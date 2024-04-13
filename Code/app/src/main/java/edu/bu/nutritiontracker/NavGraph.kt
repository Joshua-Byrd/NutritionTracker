package edu.bu.nutritiontracker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import java.util.Date

@Composable
fun NavGraph() {

    //Navigation
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "dailyDisplay"){
        composable("dailyDisplay"){
            DailyDisplay(navController)
        }
        composable("addFood/{foodId}"){
            backStackEntry ->
            val passedFoodId = backStackEntry.arguments?.getString("foodId")?.toInt()
            AddFood(navController, passedFoodId)
        }
        composable("foodSearch"){
            FoodSearch(navController)
        }
        composable("foodSearchResult/{foodName}"){
                backStackEntry ->
            backStackEntry.arguments?.getString("foodName")
                ?.let { FoodSearchResult(navController, it) }
        }
        composable("bottomMenu"){}
    }
}