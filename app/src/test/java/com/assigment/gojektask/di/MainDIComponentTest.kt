package com.assigment.gojektask.di

import com.assigment.gojektask.di.configureNetworkModuleForTest

/**
 * Main Koin DI component.
 * Helps to configure
 * 1) Mockwebserver
 * 2) Usecase
 * 3) Repository
 */
fun configureTestAppComponent(baseApi: String)
        = listOf(
    MockWebServerDIPTest,
    configureNetworkModuleForTest(baseApi),
    UseCaseDependency,
    RepositoryDependency,
    SharedPrefDependency
    )

