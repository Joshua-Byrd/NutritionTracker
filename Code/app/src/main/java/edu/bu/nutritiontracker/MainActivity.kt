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
import edu.bu.nutritiontracker.data.Food
import edu.bu.nutritiontracker.ui.theme.NutritionTrackerTheme
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
                    val currentDate = Date()
                    DailyDisplay(currentDate, getTestFoodMap())

                }
            }
        }
    }
}


//@Preview(
//    showBackground = true,
//    showSystemUi = true)
//@Composable
//fun DailyDisplayPreview() {
//    val currentDate = Date()
//    DailyDisplay(currentDate, getTestFoodMap())
//}

//@Preview(
//    showBackground = true,
//    showSystemUi = true)
//@Composable
//fun FoodSearchPreview(){
//    FoodSearch(getTestFoodMap())
//}

@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun AddFoodPreview(){
    val apple = Food("apple - 1 medium", 95.6, 0.5, 0.3,
        25.1, 0.0, 4.4)
    AddFood(apple)
}