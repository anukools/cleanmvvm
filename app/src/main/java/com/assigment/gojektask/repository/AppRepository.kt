package com.assigment.gojektask.repository

import com.assigment.gojektask.model.GitHubRepoModel
import com.assigment.gojektask.network.GithubAPIService
import org.koin.core.KoinComponent
import org.koin.core.inject

class AppRepository : KoinComponent {

    val githubAPIService: GithubAPIService by inject()
    /**
     * Request data from backend
     */
    suspend fun getRepositoryData(parameter: String): GitHubRepoModel {
        return githubAPIService.getRepository(parameter)
    }

}