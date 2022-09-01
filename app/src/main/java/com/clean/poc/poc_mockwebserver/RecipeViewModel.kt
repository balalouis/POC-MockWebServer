package com.clean.poc.poc_mockwebserver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RecipeViewModel(
    var recipeRepository: RecipeRepository,
    private val token: String,
) :
    ViewModel() {

    private var uiState: MutableStateFlow<RecipeUIState> =
        MutableStateFlow(RecipeUIState.Success(emptyList()))
    var _uiState = uiState

    fun fetchUserList(
        page: Int,
        query: String
    ) {
        viewModelScope.launch {
            recipeRepository.searchRecipeList(token, page, query)
                .catch { exception ->
                    _uiState.value = RecipeUIState.Failure(exception)
                }
                .collect {
                    _uiState.value = RecipeUIState.Success(userList = it)
                }
        }
    }

}