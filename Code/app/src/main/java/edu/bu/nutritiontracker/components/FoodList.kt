package edu.bu.nutritiontracker.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import edu.bu.nutritiontracker.data.FoodViewModel

@Composable
fun FoodList(viewModel: FoodViewModel = FoodViewModel()) {

    val foodList = viewModel.dailyFoodsWithFood.collectAsState()

    //display full list of foods eaten
    LazyColumn (
    ){
        foodList.value.forEach { entry ->
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(entry.food.name)
                    Text("${entry.dailyFoods.numServings} serving(s)",
                        modifier = Modifier
                            .widthIn(min = 48.dp),
                        textAlign = TextAlign.Start
                    )
                    val formattedCals = String.format("%.1f",(entry.food.calories * entry.dailyFoods.numServings))
                    Text("$formattedCals cals")
                }
            }
        }
    }
}