package com.assigment.gojektask.repository

import com.assigment.gojektask.model.GitHubRepoModel
import com.assigment.gojektask.network.GithubAPIService
import com.assigment.gojektask.room.RepoDBModel
import com.assigment.gojektask.room.RepositoryDOA
import com.assigment.gojektask.utils.SharePreferenceHelper
import org.koin.core.KoinComponent
import org.koin.core.inject

class AppRepository : KoinComponent {

    private val githubAPIService: GithubAPIService by inject()
    private val repoDoa : RepositoryDOA by inject()
    private val sharePref :  SharePreferenceHelper by inject()
    /**
     * Request data from backend
     */
    suspend fun getRepositoryAPI(parameter: String): GitHubRepoModel {
        return githubAPIService.getRepository(parameter)
    }

    suspend fun insertAllRepo(repoList : List<RepoDBModel>){
        repoDoa.insertAll(repoList)

    }
    fun getRepositoryFromDB(): List<RepoDBModel> {
        return repoDoa.getAllRepository()
    }

    fun setLastFetchTime(value: Long) {
        sharePref.setLastFetchTime(value)
    }

    fun getLastFetchTime(): Long {
        return sharePref.getLastFetchTime()
    }

}