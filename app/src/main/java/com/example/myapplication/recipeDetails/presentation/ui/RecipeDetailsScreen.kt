package com.example.myapplication.recipeDetails.presentation.ui

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.myapplication.common.data.remote.model.ExtendedIngredients
import com.example.myapplication.common.data.remote.model.RecipeDTO
import com.example.myapplication.components.ERHtmlText
import com.example.myapplication.recipeDetails.presentation.RecipeDetailsViewModel

@Composable
fun RecipeDetailsScreen(id: String, navHostController: NavHostController,recipeDetailsVM: RecipeDetailsViewModel, modifier: Modifier = Modifier, ) {
    val recipe by recipeDetailsVM.uiRecipe.collectAsState()
    recipeDetailsVM.fetchData(id)

    recipe?.let {
        RecipeDetailsItem(recipe,navHostController, recipeDetailsVM = recipeDetailsVM)
    }

}

@Composable
private fun RecipeDetailsItem(recipe: RecipeDTO?, navHostController: NavHostController, modifier: Modifier = Modifier, recipeDetailsVM: RecipeDetailsViewModel) {
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
                recipeDetailsVM.cleanRecipeId()
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

        recipe?.extendedIngredients.let {
            DetailedInfo(recipe?.extendedIngredients ?: emptyList())
        }
    }
}

@Composable
private fun DetailedInfo(extendedIngredients: List<ExtendedIngredients>, modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Resume Ingredients",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(4.dp))
        LazyColumn(modifier = modifier.padding(8.dp)) {
            items(extendedIngredients) { current ->
                Spacer(modifier = Modifier.size(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "name | amount: ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "${current.name} | ${current.amount}",
                        fontSize = 16.sp,
                    )
                }
            }
    }
}}
