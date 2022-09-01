package com.clean.poc.poc_mockwebserver

class RecipeTestApplication : RecipeApplication() {
    var url = "http://127.0.0.1:8080"

    override fun getBaseUrl(): String {
        return url
    }
}