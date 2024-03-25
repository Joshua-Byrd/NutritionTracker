package edu.bu.nutritiontracker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.bu.nutritiontracker.data.Food

@Composable
fun AddFood(food: Food) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text("Add Food", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        FoodPropertiesDisplay(food)

        Spacer(
            modifier = Modifier.height(10.dp)
        )



    }
}

@Composable
fun FoodPropertiesDisplay(food: Food){
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Text(food.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Row (horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text("Calories")
            Text(food.calories.toString())
        }
        Row (horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text("Protein")
            Text(food.protein.toString())
        }
        Row (horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text("Fat")
            Text(food.totalFat.toString())
        }
        Row (horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text("Carbohydrates")
            Text(food.carbohydrates.toString())
        }
        Row (horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text("Saturated Fat")
            Text(food.saturatedFat.toString())
        }
        Row (horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text("Fiber")
            Text(food.fiber.toString())
        }
    }
}