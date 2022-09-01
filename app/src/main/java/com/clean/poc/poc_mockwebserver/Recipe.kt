package com.clean.poc.poc_mockwebserver

/**
 * See Recipe example: https://food2fork.ca/
 */
data class Recipe(
    val id: Int = 0,
    val title: String = "",
    val publisher: String = "",
    val featuredImage: String = "",
    val rating: Int = 0,
    val sourceUrl: String = "",
    val ingredients: List<String> = listOf(),
    val dateAdded: String = "",
    val dateUpdated: String = "",
)

sealed class RecipeUIState {
    data class Success(var userList: List<Recipe>?) : RecipeUIState()
    data class Failure(var exception: Throwable) : RecipeUIState()
}


sealed class RecipeDetailUIState {
    data class Success(var recipe: Recipe?) : RecipeDetailUIState()
    data class Failure(var exception: Throwable) : RecipeDetailUIState()
}