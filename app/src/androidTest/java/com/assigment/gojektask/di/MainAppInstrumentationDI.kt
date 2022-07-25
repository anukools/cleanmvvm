package com.assigment.gojektask.di

/**
 * Main Koin DI component for Instrumentation Testing
 */
fun generateTestAppComponent(baseApi: String)
        = listOf(
    configureNetworkForInstrumentationTest(baseApi),
    UseCaseDependency,
    MockWebServerInstrumentationTest,
    RepositoryDependency,
    SharedPrefDependency
)