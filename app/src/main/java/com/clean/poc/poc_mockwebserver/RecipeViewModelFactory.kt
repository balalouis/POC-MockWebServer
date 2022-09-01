package com.clean.poc.poc_mockwebserver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RecipeViewModelFactory(private val recipeRepository: RecipeRepository, private val token: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeViewModel(recipeRepository, token) as T
    }
}