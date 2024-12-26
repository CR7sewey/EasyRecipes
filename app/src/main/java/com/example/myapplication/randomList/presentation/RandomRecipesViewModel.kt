package com.example.myapplication.randomList.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.myapplication.common.data.RetroFitClient
import com.example.myapplication.common.model.RecipeDTO
import com.example.myapplication.randomList.data.RandomListService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RandomRecipesViewModel(private val randomListService: RandomListService): ViewModel() {

    private val _uiRandomRecipes = MutableStateFlow<List<RecipeDTO>>(emptyList<RecipeDTO>())
    val uiRandomRecipes: StateFlow<List<RecipeDTO>> = _uiRandomRecipes

    init {
        fetchData()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                // Get the Application object from extras
                val application = RetroFitClient.retrofit.create(RandomListService::class.java)
                // Create a SavedStateHandle for this ViewModel from extras
                //val savedStateHandle = extras.createSavedStateHandle()
                return RandomRecipesViewModel(application as RandomListService) as T
            }

        }
    }

    private fun fetchData() {

        viewModelScope.launch(Dispatchers.IO) { // Suspend configuration != callback one
            val response = randomListService.getRandomRecipes()
            if (response.isSuccessful) {
                val recipes = response.body()?.recipes ?: emptyList<RecipeDTO>()
                _uiRandomRecipes.value = recipes
            }
            else {
                Log.d("MainActivity", "Request Error :: ${response.errorBody()}")
            }
        }

    }

}