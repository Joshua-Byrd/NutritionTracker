package edu.bu.nutritiontracker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.bu.nutritiontracker.data.DailyFoodsViewModel
import edu.bu.nutritiontracker.data.FoodViewModel
import edu.bu.nutritiontracker.data.db.DailyFoodEntryWithFood


@Composable
fun FoodSearch(
    navController: NavController) {

    FoodSearchScaffold(navController)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodSearchScaffold(
    navController:NavController,
    viewModel: DailyFoodsViewModel = hiltViewModel()
) {
    val date by viewModel.date.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,

                ),
                title = {
                    Text(
                        text ="$date",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("foodSearch") }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(8.dp)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text ="Enter food name",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)

            FoodSearchBar(navController)

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text ="Recent Foods",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)

            RecentFoods(navController)

        }
    }
}
@Composable
fun FoodSearchBar(
    navController: NavController,
    dailyFoodsViewModel: DailyFoodsViewModel = hiltViewModel(),
    foodViewModel: FoodViewModel = hiltViewModel()
) {

    var foodName by remember { mutableStateOf("")}

    Column(
        horizontalAlignment = Alignment.Start
    ) {

        OutlinedTextField(
            value = foodName,
            onValueChange = { foodName = it})

        Button(
            onClick = { navController.navigate("foodSearchResult/$foodName") },
        ) {
            Text("Search")
        }
    }
}

@Composable
fun RecentFoods(
    navController: NavController,
    viewModel: DailyFoodsViewModel = hiltViewModel()) {
    val dailyFoodsUiState by viewModel.foodsUiState.collectAsState()
    val date by viewModel.date.collectAsState()

    val foodList = dailyFoodsUiState.recentFoodEntriesList

    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        foodList.forEach { entry ->
            item {
                ClickableDailyFoodEntryWithFood(
                    entry = entry,
                    onClick = {
                        navController.navigate("addFood/${entry.food.foodId}")

                    }
                )
            }

        }
    }
}

@Composable
fun ClickableDailyFoodEntryWithFood(entry: DailyFoodEntryWithFood, onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick() }
    ) {
        Text("${entry.food.name} x${entry.dailyFoods.numServings} ")
        val formattedCals =
            String.format("%.1f", (entry.food.calories * entry.dailyFoods.numServings))
        Text("$formattedCals cals")
    }
}