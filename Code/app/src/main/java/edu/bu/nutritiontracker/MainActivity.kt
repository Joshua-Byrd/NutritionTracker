package edu.bu.nutritiontracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.bu.nutritiontracker.ui.theme.NutritionTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutritionTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainDisplay("03/16/2024", Modifier)
                }
            }
        }
    }
}


/**
 * Displays date, nutrition summary and list of foods eaten
 *  ---NEEDS TO ACCEPT A DATE TO DISPLAY (Date class?)---
 */
@Composable
fun MainDisplay(date: String, modifier: Modifier) {
    Column {
        Text(date)
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

@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun MainDisplayPreview() {
    MainDisplay(date = "03/16/2024", Modifier)
}

/**
 * TO DO FOR ITERATION 1
 *
 * 1. Create Food class
 * 2. Write functions: MainDisplay, FoodList, Summary
 * 3. MainActivity.kt should be able to display info from any date
 * 4. Create Activity for Searching for foods
 *
 */