package edu.bu.nutritiontracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.bu.nutritiontracker.data.Food
import edu.bu.nutritiontracker.data.FoodsViewModel
import edu.bu.nutritiontracker.ui.theme.NutritionTrackerTheme
import edu.bu.nutritiontracker.util.getTestFoodList
import edu.bu.nutritiontracker.util.getTestFoodMap
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

                    App()
                }
            }
        }
    }
}


@Composable
fun App() {
    val apple = Food("apple - 1 medium", 95.6, 0.5, 0.3,
        25.1, 0.0, 4.4)

    //Navigation
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "dailyDisplay"){
        composable("dailyDisplay"){
            DailyDisplay(navController, Date())
        }
        composable("addFood"){
            AddFood(navController, apple)
        }
        composable("foodSearch"){
            FoodSearch(navController)
        }
        composable("foodSearchResult"){
            FoodSearchResult(navController, "apple - 1 medium" , getTestFoodList() )
        }
        composable("bottomMenu"){}
    }
}


