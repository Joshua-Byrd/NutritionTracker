package edu.bu.nutritiontracker.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.bu.nutritiontracker.data.Food

@Composable
fun FoodList(foodMap: Map<Food, Int>) {

    //display full list of foods eaten
    LazyColumn (
    ){
        foodMap.forEach { entry ->
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(entry.key.name)
                    Text("${entry.value} serving(s)",
                        modifier = Modifier
                            .widthIn(min = 48.dp),
                        textAlign = TextAlign.Start
                    )
                    val formattedCals = String.format("%.1f",(entry.key.calories * entry.value))
                    Text("$formattedCals cals")
                }
            }
        }
    }
}