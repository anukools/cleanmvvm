package com.assigment.gojektask.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assigment.gojektask.model.GitHubRepoModel
import com.assigment.gojektask.repository.AppRepository
import com.assigment.gojektask.utils.LiveDataWrapper
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class GitHubViewModel(
        mainDispatcher: CoroutineDispatcher,
        ioDispatcher: CoroutineDispatcher
) : ViewModel(), KoinComponent {
    val appRepository : AppRepository by inject()
    var repoLiveData = MutableLiveData<LiveDataWrapper<GitHubRepoModel>>()
    private val job = SupervisorJob()
    val mUiScope = CoroutineScope(mainDispatcher + job)
    val mIoScope = CoroutineScope(ioDispatcher + job)


    //can be called from View explicitly if required
    //Keep default scope
    fun getRepositoryData(param:String) {
            mUiScope.launch {
                repoLiveData.value = LiveDataWrapper.loading()
                try {
                    val data = mIoScope.async {
                        return@async appRepository.getRepositoryData(param)
                    }.await()
                    try {
                        repoLiveData.value = LiveDataWrapper.success(data)
                    } catch (e: Exception) {
                    }
                } catch (e: Exception) {
                    repoLiveData.value = LiveDataWrapper.error(e)
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}