package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun RandomRecipesScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    var randomRecipes by rememberSaveable { mutableStateOf<List<RecipeDTO>>(emptyList()) }
    val apiService = RetroFitClient.retrofit.create(ApiService::class.java)
    val callGetRandomRecipes = apiService.getRandomRecipes()

    if (randomRecipes.isEmpty()) {
    callGetRandomRecipes.enqueue(object : Callback<RecipeResponse> {
        override fun onResponse(
            call: Call<RecipeResponse?>,
            response: Response<RecipeResponse?>
        ) {
            println("AQUI ZE")
            println(response)
            if (response.isSuccessful) {
                val recipes = response.body()?.recipes ?: emptyList()

                randomRecipes = recipes


            }
            else {
                Log.d("MainActivity", "Request Error :: ${response.errorBody()}")
            }
        }

        override fun onFailure(call: Call<RecipeResponse?>, t: Throwable) {
            Log.d("MainActivity", "Network Error :: ${t.message}")

        }
    })}
    Surface (
        modifier = Modifier
            .fillMaxSize()
    ) {
        RecipesList(randomRecipes)
    }


}

@Composable
fun RecipesList(recipes: List<RecipeDTO>, modifier: Modifier = Modifier) {

        LazyColumn(modifier = modifier.padding(8.dp)) {
            items(recipes) { current ->
                Text(current.title)
            }
        }

    
}

@Composable
fun Recipe(modifier: Modifier = Modifier) {
    
}