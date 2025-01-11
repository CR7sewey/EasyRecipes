package com.example.myapplication.recipeDetails.presentation

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.myapplication.common.data.remote.RetroFitClient
import com.example.myapplication.common.data.remote.model.RecipeDTO
import com.example.myapplication.recipeDetails.data.RecipeDetailsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.toString

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(private val recipeDetailsService: RecipeDetailsService): ViewModel() {

    private val _uiRecipe = MutableStateFlow<RecipeDTO?>(null)
    val uiRecipe: StateFlow<RecipeDTO?> = _uiRecipe

    private val _uiErrorFetching = MutableStateFlow<String>("")
    val uiErrorFetching: StateFlow<String> = _uiErrorFetching

    /*companion object {
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
    }*/

    fun cleanRecipeId() {
        viewModelScope.launch{
            delay(1000)
            _uiRecipe.value = null
            _uiErrorFetching.value = ""
        }

    }

    fun fetchData(itemId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = recipeDetailsService.getRecipeInfo(itemId.toString())
                if (response.isSuccessful) {
                    _uiRecipe.value = response.body()
                } else {
                    Log.d("RecipeDetailsViewModel", "Request Error :: ${response.errorBody()}")
                    _uiErrorFetching.value = NetworkErrorException(response.message().toString()).message.toString()
                }
            }
            catch (ex: Exception) {
                ex.printStackTrace()
                println("AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII 222")
                var errorMessage = "Something went wrong..."
                if (ex is UnknownHostException) {
                    errorMessage = "No internet connection..."//ex.message.toString()
                }
                _uiErrorFetching.value = errorMessage
            }
        }
    }

}