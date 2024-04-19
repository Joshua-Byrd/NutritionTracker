package edu.bu.nutritiontracker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.bu.nutritiontracker.data.DailyFoodsViewModel
import edu.bu.nutritiontracker.data.FoodViewModel
import edu.bu.nutritiontracker.data.db.DailyFoodEntryWithFood
import edu.bu.nutritiontracker.data.db.Food
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@Composable
fun FoodSearchResult(
    navController: NavController,
    foodName: String,
) {
    FoodSearchResultScaffold(navController, foodName)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodSearchResultScaffold(
    navController:NavController,
    foodName: String,
    viewModel: DailyFoodsViewModel = hiltViewModel()
) {
    val date by viewModel.date.collectAsState()
    val formattedDate = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))


    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,

                    ),
                title = {
                    Text(
                        text = formattedDate,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        },

//        floatingActionButton = {
//            FloatingActionButton(onClick = { navController.navigate("foodSearch") }) {
//               Icon(Icons.Default.Add, contentDescription = "Add")
//                Text(
//                    text = "Add Food",
//                    modifier = Modifier.padding(8.dp),
//                    color = Color(0xFF4C662B)
//                )
//            }
//        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(8.dp)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text ="Search results",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)

            FoodSearchDisplay(foodName, navController)

            Spacer(modifier = Modifier.padding(8.dp))


        }
    }
}

@Composable
fun FoodSearchDisplay(
    foodName: String,
    navController: NavController,
    foodViewModel: FoodViewModel = hiltViewModel()) {

    val foodList by foodViewModel.getFoodListByName(foodName).collectAsState(initial = emptyList())

    LazyColumn(
    ) {
        foodList.forEach { entry ->
            item {
                ClickableFoodSearchEntry(
                    entry = entry,
                    onClick = {
                        navController.navigate("addFood/${entry.foodId}")

                    }
                )
            }

        }
    }
}


@Composable
fun ClickableFoodSearchEntry(entry: Food, onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick() }
    ) {
        Text(entry.name)
        val formattedCals =
            String.format("%.1f", (entry.calories))
        Text("$formattedCals cals")
    }
}