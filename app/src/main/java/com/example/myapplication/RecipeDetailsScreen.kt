package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.myapplication.components.ERHtmlText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun RecipeDetailsScreen(id: String, navHostController: NavHostController, modifier: Modifier = Modifier) {
    var recipe by remember { mutableStateOf<RecipeDTO?>(null) }
    val apiService = RetroFitClient.retrofit.create(ApiService::class.java)
    val callGetRecipe = apiService.getRecipeInfo(id)


    callGetRecipe.enqueue(object : Callback<RecipeDTO> {
            override fun onResponse(
                call: Call<RecipeDTO?>,
                response: Response<RecipeDTO?>
            ) {
                println("AQUI ZE")
                println(response)
                if (response.isSuccessful) {
                    recipe = response.body()
                }
                else {
                    Log.d("MainActivity", "Request Error :: ${response.errorBody()}")
                }
            }
            override fun onFailure(call: Call<RecipeDTO?>, t: Throwable) {
                Log.d("MainActivity", "Network Error :: ${t.message}")

            }
        })

    recipe?.let {
        RecipeDetailsItem(recipe,navHostController)
    }

}

@Composable
private fun RecipeDetailsItem(recipe: RecipeDTO?, navHostController: NavHostController, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize().padding(8.dp).verticalScroll(rememberScrollState())) {
        /* LazyRow {
             items(listOf(movie)) {
                     mov ->Text(text = mov?.title ?: "Empty title", fontSize = 48.sp) }
         }*/
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                navHostController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Button"
                )
            }

            LazyRow {
                items(listOf(recipe)) {
                        r ->Text(text = r?.title ?: "", fontSize = 38.sp) }
            }

            /*Text(
                modifier = Modifier.padding(start = 4.dp),
                text = movie?.title ?: "Empty title", fontSize = 48.sp,

            )*/
        }


        AsyncImage(model = recipe?.image, contentDescription = "${recipe?.title} Poster Image",
            modifier = Modifier
                .height(200.dp)
                .fillMaxSize(),
            contentScale = ContentScale.Crop,

            )
        Spacer(modifier = Modifier.size(4.dp))

        ERHtmlText(textHTML = recipe?.summary ?: "" )

    }
}