package com.assigment.gojektask.di

import com.assigment.gojektask.repository.AppRepository
import org.koin.dsl.module

/**
 * Repository DI module.
 * Provides Repo dependency.
 */

val RepositoryDependency = module {

    factory {
        AppRepository()
    }

}