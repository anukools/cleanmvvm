package com.assigment.gojektask

import androidx.multidex.MultiDexApplication
import com.assigment.gojektask.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class.
 * Dependency Injection initiated for all sub modules.
 */
open class GoJekApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initiateKoin()
    }

    private fun initiateKoin() {
        startKoin{
            androidContext(this@GoJekApplication)
            modules(provideDependency())
        }
    }

    open fun provideDependency() = appComponent
}