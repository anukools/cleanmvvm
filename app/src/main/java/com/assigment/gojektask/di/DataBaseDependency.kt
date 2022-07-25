package com.assigment.gojektask.di

import android.app.Application
import androidx.room.Room
import com.assigment.gojektask.room.RepoDB
import com.assigment.gojektask.room.RepositoryDOA
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val DataBaseDependency = module {
    fun provideDataBase(application: Application): RepoDB {
        return Room.databaseBuilder(application, RepoDB::class.java, "GITHUBDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(dataBase: RepoDB): RepositoryDOA {
        return dataBase.repositoryDOA
    }
    single<RepoDB> { provideDataBase(androidApplication()) }
    single<RepositoryDOA> { provideDao(get()) }

}