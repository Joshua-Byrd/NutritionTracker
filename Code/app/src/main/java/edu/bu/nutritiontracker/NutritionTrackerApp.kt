package edu.bu.nutritiontracker

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.util.Date

@Composable
fun NutritionTrackerApp() {

    //Navigation
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "dailyDisplay"){
        composable("dailyDisplay"){
            DailyDisplay(navController, Date())
        }
        composable("addFood"){
            AddFood(navController)
        }
        composable("foodSearch"){
            FoodSearch(navController)
        }
        composable("foodSearchResult"){
            FoodSearchResult(navController, "apple - 1 medium")
        }
        composable("bottomMenu"){}
    }
}