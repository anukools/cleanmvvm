package com.assigment.gojektask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.assigment.gojektask.base.BaseTest
import com.assigment.gojektask.di.configureTestAppComponent
import com.assigment.gojektask.repository.AppRepository
import com.assigment.gojektask.room.RepoDBModel
import com.assigment.gojektask.room.RepositoryDOA
import com.assigment.gojektask.screen.GitHubViewModel
import com.assigment.gojektask.utils.LiveDataWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject

@RunWith(JUnit4::class)
class ViewModelTest: BaseTest(){

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun start(){
        super.setUp()
        //Used for initiation of Mockk
        MockKAnnotations.init(this)
        //Start Koin with required dependencies
        startKoin{  modules(configureTestAppComponent(getMockWebServerUrl()))}
    }
}