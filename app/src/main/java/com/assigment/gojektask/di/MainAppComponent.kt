package com.assigment.gojektask.di

/**
 *  Dependency component.
 * This will create and provide required dependencies with sub dependencies.
 */
val appComponent = listOf(NetworkDependency, DataBaseDependency,  RepositoryDependency, SharedPrefDependency)