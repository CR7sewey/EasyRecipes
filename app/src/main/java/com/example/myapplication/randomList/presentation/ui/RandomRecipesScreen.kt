package com.example.myapplication.randomList.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.myapplication.common.model.RecipeDTO
import com.example.myapplication.components.ERHtmlText
import com.example.myapplication.components.ERSearchBar
import com.example.myapplication.randomList.presentation.RandomRecipesViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RandomRecipesScreen(navController: NavHostController, modifier: Modifier = Modifier, randomRecipesVM: RandomRecipesViewModel) {
    // val toBeDeleted = "\"Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs might be a good recipe to expand your main course repertoire. One portion of this dish contains approximately &lt;b&gt;19g of protein &lt;/b&gt;,  &lt;b&gt;20g of fat &lt;/b&gt;, and a total of  &lt;b&gt;584 calories &lt;/b&gt;. For  &lt;b&gt;\$1.63 per serving &lt;/b&gt;, this recipe  &lt;b&gt;covers 23% &lt;/b&gt; of your daily requirements of vitamins and minerals. This recipe serves 2. It is brought to you by fullbellysisters.blogspot.com. 209 people were glad they tried this recipe. A mixture of scallions, salt and pepper, white wine, and a handful of other ingredients are all it takes to make this recipe so scrumptious. From preparation to the plate, this recipe takes approximately  &lt;b&gt;45 minutes &lt;/b&gt;. All things considered, we decided this recipe  &lt;b&gt;deserves a spoonacular score of 83% &lt;/b&gt;. This score is awesome. If you like this recipe, take a look at these similar recipes: &lt;a href=\\\"https://spoonacular.com/recipes/cauliflower-gratin-with-garlic-breadcrumbs-318375\\\">Cauliflower Gratin with Garlic Breadcrumbs&lt;/a&gt;, &lt; href=\\\"https://spoonacular.com/recipes/pasta-with-cauliflower-sausage-breadcrumbs-30437\\\">Pasta With Cauliflower, Sausage, & Breadcrumbs&lt;/a&gt;, and &lt;a href=\\\"https://spoonacular.com/recipes/pasta-with-roasted-cauliflower-parsley-and-breadcrumbs-30738\\\">Pasta With Roasted Cauliflower, Parsley, And Breadcrumbs&lt;/a&gt;.\""


    val randomRecipes by randomRecipesVM.uiRandomRecipes.collectAsState()

        /*randomRecipes = listOf(
        RecipeDTO(1,"Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs","https://spoonacular.com/recipeImages/716429-556x370.jpg","2","45",toBeDeleted,listOf(
            ExtendedIngredients(1,2,"butter-sliced.jpg","butter"))),
        RecipeDTO(2,"Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs","https://spoonacular.com/recipeImages/716429-556x370.jpg","2","45",toBeDeleted,listOf(
            ExtendedIngredients(1,2,"butter-sliced.jpg","butter"))),
        RecipeDTO(3,"Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs","https://spoonacular.com/recipeImages/716429-556x370.jpg","2","45",toBeDeleted,listOf(
            ExtendedIngredients(1,2,"butter-sliced.jpg","butter"))))*/

    Surface (
        modifier = Modifier
            .fillMaxSize()
    ) {
        randomRecipes.let {
            RecipesList(randomRecipes, navController, onClick = { itemClicked ->
                navController.navigate("recipe_detail/${itemClicked.id}")
            })
        }
    }


}

@Composable
private fun RecipesList(recipes: List<RecipeDTO>, navController: NavHostController, onClick: (RecipeDTO) -> Unit, modifier: Modifier = Modifier) {

        Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = "Find the best recipes for cooking!",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                lineHeight = 40.sp,
                fontWeight = FontWeight.Bold)
            SearchBar(navController)
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = "Recipes you may like!",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.size(8.dp))
            LazyColumn(modifier = modifier.padding(8.dp)) {
                items(recipes) { current ->
                    Recipe(current, onClick)
                }
            }
        }
    
}

@Composable
fun SearchBar(navController: NavHostController, modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }
    ERSearchBar(placeholder = "Search for a recipe", query = query, onValueChange = {it -> query = it}, onSearchClicked = {
        val searchedItem = query.trim()
        println("AQUIIIII 3")
        if (searchedItem.isNotEmpty()) {
            navController.navigate(route = "search_recipes/${searchedItem}")
        }
        //navController.navigate("")
        println("AQUIIIII")
    })
}

@Composable
fun Recipe(recipeDTO: RecipeDTO, onClick: (itemClicked: RecipeDTO) -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .padding(8.dp)
        .fillMaxWidth().clickable {
            onClick.invoke(recipeDTO)
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
        Spacer(modifier = Modifier.size(8.dp))

            ERHtmlText(textHTML = recipeDTO.summary, MaxLines = 3)
       /* Text(
            text = "${HtmlCompat.fromHtml(recipeDTO.summary,0)}",
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 6.dp),
            fontWeight = FontWeight.SemiBold,

        )*/
    }
} }