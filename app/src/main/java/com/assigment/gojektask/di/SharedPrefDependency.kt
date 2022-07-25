package com.assigment.gojektask.di

import com.assigment.gojektask.utils.SharePreferenceHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Shared Preference DI Module.
 *
 */
val SharedPrefDependency = module {

    factory { SharePreferenceHelper(androidContext()) }
}