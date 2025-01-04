package com.example.myapplication.randomList.presentation

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.myapplication.MyRecipesApplication
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.example.myapplication.common.data.model.Recipe
import com.example.myapplication.common.data.remote.model.RecipeDTO
import com.example.myapplication.randomList.data.RecipesListRepository
import com.example.myapplication.randomList.data.remote.RandomListService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class RandomRecipesViewModel(private val recipesListRepository: RecipesListRepository): ViewModel() {

    private val _uiRandomRecipes = MutableStateFlow<List<Recipe>>(emptyList<Recipe>())
    val uiRandomRecipes: StateFlow<List<Recipe>> = _uiRandomRecipes

    private val _uiErrorFetching = MutableStateFlow<String>("")
    val uiErrorFetching: StateFlow<String> = _uiErrorFetching

    init {
        fetchData()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                // Get the Application object from extras
                //val application = RetroFitClient.retrofit.create(RandomListService::class.java)
                // Create a SavedStateHandle for this ViewModel from extras
                val application = checkNotNull(extras[APPLICATION_KEY]) as MyRecipesApplication
                val repository = application.repository
                //val savedStateHandle = extras.createSavedStateHandle()
                return RandomRecipesViewModel(repository) as T
            }

        }
    }

    private fun fetchData() {

        viewModelScope.launch(Dispatchers.IO) { // Suspend configuration != callback one
            val response = recipesListRepository.getAllRecipes()
            if (response.isSuccess) {
                val recipes = response.getOrNull()
                if (recipes != null) {
                    _uiRandomRecipes.value = recipes
                }

            }
            else {
                Log.d("MainActivity", "Request Error :: ${response.exceptionOrNull()?.message.toString()}")
                val ex = response.exceptionOrNull()
                _uiErrorFetching.value = "Some error..."
                if (ex is NetworkErrorException) {
                    _uiErrorFetching.value = "No internet connection..."
                }
            }
        }

    }

}