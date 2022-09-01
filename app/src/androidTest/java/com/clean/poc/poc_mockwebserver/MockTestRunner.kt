package com.clean.poc.poc_mockwebserver

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class MockTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?, className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, RecipeTestApplication::class.java.name, context)
    }
}
