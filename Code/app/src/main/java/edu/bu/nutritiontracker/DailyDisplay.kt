package edu.bu.nutritiontracker

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import edu.bu.nutritiontracker.data.Food
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Displays date, nutrition summary and list of foods eaten
 *  ---NEEDS TO ACCEPT A DATE TO DISPLAY (Date class?)---
 */
@Composable
fun DailyDisplay(date: Date) {
    Column {

        val formattedDate = SimpleDateFormat("MM/dd/yyyy").format(date)
        Text(formattedDate)
    }
}

/**
 * Displays summary of calories and macronutrients
 */
@Composable
fun Summary(foodMap: Map<String, Int>){}

/**
 * Displays list of foods eaten ---NEEDS TO ACCEPT A LIST OF FOOD OBJECTS---
 */
@Composable
fun FoodList(foodList: List<Food>){

}