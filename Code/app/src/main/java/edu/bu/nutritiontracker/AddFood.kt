package edu.bu.nutritiontracker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.bu.nutritiontracker.components.BottomMenu
import edu.bu.nutritiontracker.components.FoodList
import edu.bu.nutritiontracker.data.DailyFoodsViewModel
import edu.bu.nutritiontracker.data.db.Food
import edu.bu.nutritiontracker.data.FoodViewModel

@Composable
fun AddFood(navController: NavController, foodId: Int) {
    AddFoodScaffold(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFoodScaffold(
    navController:NavController,
    dailyFoodsviewModel: DailyFoodsViewModel = hiltViewModel(),
    foodViewModel: FoodViewModel = hiltViewModel()
) {
    val date by dailyFoodsviewModel.date.collectAsState()

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
                text ="Summary",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center)
            Summary()

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text ="Foods Eaten",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center)

            FoodList()

        }
    }
}

    @Composable
    fun FoodPropertiesDisplay(
        foodId: Int,
        viewModel: FoodViewModel = hiltViewModel()) {

        val uiState by viewModel.foodUiState.collectAsState()
        val food = uiState.food
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (food != null) {
                Text(
                    "${food.name}, ${food.serving}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Text("Calories")
                    Text(food.calories.toString())
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Text("Protein")
                    Text(food.protein.toString())
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Text("Fat")
                    Text(food.totalFat.toString())
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Text("Carbohydrates")
                    Text(food.carbohydrates.toString())
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Text("Saturated Fat")
                    Text(food.saturatedFat.toString())
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Text("Fiber")
                    Text(food.fiber.toString())
                }
            }
        }
    }

    @Composable
    fun AddFoodConfirm() {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Number of servings")
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.width(200.dp)
                )

                Button(
                    onClick = { /*TODO*/ },
                ) {
                    Text("Add")
                }
            }

        }
    }
}
