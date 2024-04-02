package edu.bu.nutritiontracker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.bu.nutritiontracker.components.BottomMenu
import edu.bu.nutritiontracker.data.FoodViewModel

@Composable
fun FoodSearchResult(
    navController: NavController,
    name: String,
    viewModel: FoodViewModel = FoodViewModel()
) {

    val foodList = viewModel.foodSearchResultList.collectAsState()

    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
    ){
        Text("Results for $name", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        foodList.value.forEach{
                food ->
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(food.name.toString())
                Text(food.calories.toString())
            }
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        BottomMenu(navController) {

        }
    }


}

@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun FoodSearchResultPreview(viewModel: FoodViewModel = FoodViewModel()){
    val foodList = viewModel.foodSearchResultList.collectAsState()
    val navController = rememberNavController()
    FoodSearchResult(navController, name = "Apple")

}