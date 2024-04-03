package edu.bu.nutritiontracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import dagger.hilt.android.AndroidEntryPoint
import edu.bu.nutritiontracker.data.Food
import edu.bu.nutritiontracker.data.NutritionDatabase
import edu.bu.nutritiontracker.ui.theme.NutritionTrackerTheme
import edu.bu.nutritiontracker.util.getTestFoodList
import java.util.Date

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var nutritionDatabase: NutritionDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nutritionDatabase = Room.databaseBuilder(
            applicationContext, NutritionDatabase::class.java,
            "nutritition-db"
        ).build()

        setContent {
            NutritionTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NutritionTrackerApp()
                }
            }
        }
    }
}





