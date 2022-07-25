package com.assigment.gojektask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.assigment.gojektask.base.BaseTest
import com.assigment.gojektask.di.configureTestAppComponent
import com.assigment.gojektask.network.GithubAPIService
import com.assigment.gojektask.repository.AppRepository
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class AppRepositoryTest : BaseTest(){

    //Target
    private lateinit var mRepo: AppRepository
    //Inject api service created with koin
    val mAPIService : GithubAPIService by inject()
    //Inject Mockwebserver created with koin
    val mockWebServer : MockWebServer by inject()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val org = "octokit"

    @Before
    fun start(){
        super.setUp()

        startKoin{ modules(configureTestAppComponent(getMockWebServerUrl()))}
    }

    @Test
    fun test_repo_retrieves_expected_data() =  runBlocking<Unit>{

        mockNetworkResponseWithFileContent("sample_api_response.json", HttpURLConnection.HTTP_OK)
        mRepo = AppRepository()

        val dataReceived = mRepo.getRepositoryAPI(org)[0]

        Assert.assertNotNull(dataReceived)
        Assert.assertEquals(dataReceived.id, 1296269)
        Assert.assertEquals(dataReceived.owner.login, "octocat")
    }
}