package com.clean.poc.poc_mockwebserver

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeRepository(var recipeApiService: RecipeApiService, var recipeMapper: RecipeMapper) {
    fun searchRecipeList(
        token: String,
        page: Int,
        query: String
    ): Flow<List<Recipe>> =
        flow {
            val recipeSearchList = recipeApiService.search(token, page, query)
            emit(recipeMapper.toDomainList(recipeSearchList.recipes))
        }
}