package com.example.myapplication.randomList.presentation.ui


data class RecipesListUiState(
    val list: List<RecipeUiData> = emptyList<RecipeUiData>(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = "Something went wrong",
)


data class RecipeUiData(
    val id: Int,
    val title: String,
    val image: String,
    val servings: String,
    val readyInMinutes: String,
    val summary: String,
)