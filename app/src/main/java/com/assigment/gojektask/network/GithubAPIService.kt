package com.assigment.gojektask.network

import com.assigment.gojektask.model.GitHubRepoModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubAPIService {

    @GET("/orgs/{ORG}/repos")
    suspend fun getRepository(@Path("ORG") organization: String): GitHubRepoModel
}