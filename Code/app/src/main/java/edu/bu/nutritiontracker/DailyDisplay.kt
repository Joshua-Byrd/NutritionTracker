package edu.bu.nutritiontracker

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.util.Date

/**
 * Displays date, nutrition summary and list of foods eaten
 *  ---NEEDS TO ACCEPT A DATE TO DISPLAY (Date class?)---
 */
@Composable
fun DailyDisplay(date: Date, modifier: Modifier) {
    Column {
        date.toString()
    }
}

/**
 * Displays summary of calories and macronutrients
 */
@Composable
fun Summary(
    calories: Int,
    carbs: Int,
    protein: Int,
    fat: Int,
    satFat: Int,
    fiber: Int
){}

/**
 * Displays list of foods eaten ---NEEDS TO ACCEPT A LIST OF FOOD OBJECTS---
 */
@Composable
fun FoodList(){}