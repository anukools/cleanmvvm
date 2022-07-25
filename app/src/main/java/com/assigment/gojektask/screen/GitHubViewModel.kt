package com.assigment.gojektask.screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assigment.gojektask.repository.AppRepository
import com.assigment.gojektask.room.RepoDBModel
import com.assigment.gojektask.utils.LiveDataWrapper
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.concurrent.TimeUnit

class GitHubViewModel(
    ioDispatcher: CoroutineDispatcher
) : ViewModel(), KoinComponent {
    private val appRepository: AppRepository by inject()
    var repoLiveData = MutableLiveData<LiveDataWrapper<List<RepoDBModel>>>()
    private val job = SupervisorJob()
    private val mIoScope = CoroutineScope(ioDispatcher + job)

    fun checkForDataSource(param: String, isForce: Boolean) {
        if (shouldForceUpdate(isForce, appRepository)) {
            callRepositoryAPI(param)
        } else {
            getRepoListFromLocalCache()
        }
    }

    fun shouldForceUpdate(isForce: Boolean, appRepository: AppRepository): Boolean {
        // lastFetchTime denotes first time call after that respective time difference will be checked
        val lastFetchTime = appRepository.getLastFetchTime()
        val currentTime = System.currentTimeMillis()
        // for testing
//        val fetchDifferenceInMinutes = TimeUnit.MILLISECONDS.toMinutes(currentTime -  lastFetchTime)
        val fetchDifferenceInHours = TimeUnit.MILLISECONDS.toHours(currentTime - lastFetchTime)

        Log.d("GitHubViewModel", "fetchDifferenceInMinutes: $fetchDifferenceInHours")
        return lastFetchTime == 0L || isForce || fetchDifferenceInHours >= 2
    }

    private fun getRepoListFromLocalCache() {
        viewModelScope.launch {
            repoLiveData.value = LiveDataWrapper.loading()
            try {
                val data = mIoScope.async {
                    return@async appRepository.getRepositoryFromDB()
                }.await()
                repoLiveData.value = LiveDataWrapper.success(data)
            } catch (e: Exception) {
                repoLiveData.value = LiveDataWrapper.error(e)
            }

        }
    }

    fun callRepositoryAPI(param: String) {
        viewModelScope.launch {
            repoLiveData.value = LiveDataWrapper.loading()
            try {
                val apiData = mIoScope.async {
                    return@async appRepository.getRepositoryAPI(param)
                }.await()

                //INSERT data into db
                withContext(mIoScope.coroutineContext) {
                    //INSERT data into db
                    val list: MutableList<RepoDBModel> = mutableListOf()
                    for (repo in apiData) {
                        val localObject = RepoDBModel(
                                repo.id,
                                repo.name,
                                repo.description,
                                repo.html_url,
                                repo.owner.avatar_url,
                                repo.language,
                                repo.stargazers_count,
                                repo.forks_count
                        )
                        list.add(localObject)
                    }
                    appRepository.insertAllRepo(list)

                    appRepository.setLastFetchTime(System.currentTimeMillis())
                }
                // Now show the latest data
                getRepoListFromLocalCache()

            } catch (e: Exception) {
                e.printStackTrace()
                repoLiveData.value = LiveDataWrapper.error(e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}