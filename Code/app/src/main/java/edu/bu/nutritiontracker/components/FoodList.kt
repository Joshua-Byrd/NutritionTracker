package edu.bu.nutritiontracker.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.bu.nutritiontracker.data.DailyFoodsUiState
import edu.bu.nutritiontracker.data.DailyFoodsViewModel
import edu.bu.nutritiontracker.data.FoodViewModel

@Composable
fun FoodList(viewModel: DailyFoodsViewModel = hiltViewModel()) {

    val dailyFoodsUiState by viewModel.foodsUiState.collectAsState()
    val date by viewModel.date.collectAsState()

    val foodList = dailyFoodsUiState.dailyFoodsWithFoodByDate
    //display full list of foods eaten
    LazyColumn (
    ){
        foodList.forEach { entry ->
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("${entry.food.name} x${entry.dailyFoods.numServings} ")
                    val formattedCals = String.format("%.1f",(entry.food.calories * entry.dailyFoods.numServings))
                    Text("$formattedCals cals")
                }
            }
        }
    }
}