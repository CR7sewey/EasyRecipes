package com.example.myapplication.recipeDetails.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.myapplication.common.data.RetroFitClient
import com.example.myapplication.common.model.RecipeDTO
import com.example.myapplication.recipeDetails.data.RecipeDetailsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(private val recipeDetailsService: RecipeDetailsService): ViewModel() {

    private val _uiRecipe = MutableStateFlow<RecipeDTO?>(null)
    val uiRecipe: StateFlow<RecipeDTO?> = _uiRecipe

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                // Get the Application object from extras
                val application = RetroFitClient.retrofit.create(RecipeDetailsService::class.java)
                // Create a SavedStateHandle for this ViewModel from extras
                //val savedStateHandle = extras.createSavedStateHandle()
                return RecipeDetailsViewModel(application as RecipeDetailsService) as T
            }

        }
    }

    fun cleanRecipeId() {
        viewModelScope.launch{
            delay(1000)
            _uiRecipe.value = null
        }

    }

    fun fetchData(itemId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = recipeDetailsService.getRecipeInfo(itemId.toString())
            if (response.isSuccessful) {
                _uiRecipe.value = response.body()
            }
            else {
                Log.d("RecipeDetailsViewModel", "Request Error :: ${response.errorBody()}")
            }
        }
    }

}