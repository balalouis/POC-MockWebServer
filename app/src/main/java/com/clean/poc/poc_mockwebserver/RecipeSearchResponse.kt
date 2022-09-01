package com.clean.poc.poc_recipe.network.model

import com.clean.poc.poc_mockwebserver.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(

    @SerializedName("count")
        var count: Int,

    @SerializedName("results")
        var recipes: List<RecipeDto>,
)