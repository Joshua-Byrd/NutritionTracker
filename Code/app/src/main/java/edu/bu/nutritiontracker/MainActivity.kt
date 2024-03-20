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
import edu.bu.nutritiontracker.Food
import edu.bu.nutritiontracker.DailyDisplay
import java.util.Date

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

                }
            }
        }
    }
}








@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun DailyDisplayPreview() {
    DailyDisplay()
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