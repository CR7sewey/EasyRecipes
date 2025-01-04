package com.example.myapplication.common.data.model

import com.example.myapplication.common.data.remote.model.ExtendedIngredients

data class Recipe(
    val id: Int,
    val title: String,
    val image: String,
    val servings: String,
    val readyInMinutes: String,
    val summary: String,
)
