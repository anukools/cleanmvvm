package com.assigment.gojektask.app

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.assigment.gojektask.app.TestMainApp

/**
 * Custom Instrumentation Test runner.
 * Helps to configure environment with new App instance.
 */
class CustomInstrumentationRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader,
                                className: String,
                                context: Context): Application {
        return super.newApplication(cl,
            TestMainApp::class.java.name,
            context)
    }
}