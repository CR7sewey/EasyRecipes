package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.myapplication.components.ERHtmlText
import com.example.myapplication.components.ERSearchBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun SearchRecipesScreen(query: String, navHostController: NavHostController, modifier: Modifier = Modifier) {
    var recipes by remember { mutableStateOf<List<SearchRecipeDto>>(emptyList()) }
    val apiService = RetroFitClient.retrofit.create(ApiService::class.java)
    val callSearchRecipes = apiService.searchRecipes(query)


    callSearchRecipes.enqueue(object : Callback<SearchRecipesResponse> {
        override fun onResponse(
            call: Call<SearchRecipesResponse?>,
            response: Response<SearchRecipesResponse?>
        ) {
            println("AQUI ZE")
            println(response)
            if (response.isSuccessful) {
                recipes = response.body()?.results ?: emptyList()
            }
            else {
                Log.d("MainActivity", "Request Error :: ${response.errorBody()}")
            }
        }
        override fun onFailure(call: Call<SearchRecipesResponse?>, t: Throwable) {
            Log.d("MainActivity", "Network Error :: ${t.message}")

        }
    })
    Surface (
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchedRecipesList(recipes, navHostController, query)
    }


}

@Composable
private fun SearchedRecipesList(recipes: List<SearchRecipeDto>, navController: NavHostController, query: String, modifier: Modifier = Modifier) {

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
    ) {
        /* LazyRow {
             items(listOf(movie)) {
                     mov ->Text(text = mov?.title ?: "Empty title", fontSize = 48.sp) }
         }*/
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Button"
                )
            }

            Text(text = query, fontSize = 26.sp)

            /*Text(
                modifier = Modifier.padding(start = 4.dp),
                text = movie?.title ?: "Empty title", fontSize = 48.sp,

            )*/
        }

        LazyColumn(modifier = modifier.padding(8.dp)) {
            items(recipes) { current ->
                Recipe(current, navController)
            }
        }
    }

}

@Composable
fun Recipe(recipeDTO: SearchRecipeDto, navHostController: NavHostController, modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clickable {
             navHostController.navigate(route = "recipe_detail"+ "/${recipeDTO.id}")
        }) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onSecondary,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),

            ) {
            AsyncImage(
                model = recipeDTO.image, contentDescription = "${recipeDTO.title} Recipe Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = recipeDTO.title,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 6.dp),
                fontWeight = FontWeight.SemiBold
            )
        }
    } }