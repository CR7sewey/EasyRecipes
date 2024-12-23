package com.example.myapplication


data class RecipeDTO(
    val id: Int,
    val title: String,
    val image: String,
    val servings: String,
    val readyInMinutes: String,
    val summary: String,
    val extendedIngredients: List<ExtendedIngredients>
)

data class ExtendedIngredients(
    val id: Int,
    val amount: Float,
    val image: String,
    val name: String
)