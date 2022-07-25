package com.assigment.gojektask.app

import com.assigment.gojektask.GoJekApplication
import org.koin.core.module.Module

/**
 * Helps to configure required dependencies for Instru Tests.
 * Method provideDependency can be overrided and new dependencies can be supplied.
 */
class TestMainApp : GoJekApplication() {
    override fun provideDependency() = listOf<Module>()
}