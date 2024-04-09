package edu.bu.nutritiontracker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.bu.nutritiontracker.components.BottomMenu
import edu.bu.nutritiontracker.data.db.Food
import edu.bu.nutritiontracker.data.FoodViewModel

@Composable
fun AddFood(navController: NavController, viewModel: FoodViewModel = hiltViewModel()) {

//    val food = viewModel.food.collectAsState()
//
//    Column(
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxSize()
//    ) {
//        Text("Add Food", fontSize = 24.sp, fontWeight = FontWeight.Bold)
//
//        Spacer(
//            modifier = Modifier.height(10.dp)
//        )
//
//        FoodPropertiesDisplay(food.value)
//
//        Spacer(
//            modifier = Modifier.height(10.dp)
//        )
//
//        AddFoodConfirm()
//
//        Spacer(
//            modifier = Modifier.height(10.dp)
//        )
//
//        BottomMenu (navController){
//
//        }
//
//
//    }
//}
//
//@Composable
//fun FoodPropertiesDisplay(food: Food?){
//    Column(
//        horizontalAlignment = Alignment.Start,
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        if(food != null) {
//            Text("${food.name}, ${food.serving}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier.fillMaxWidth()
//            )
//            {
//                Text("Calories")
//                Text(food.calories.toString())
//            }
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier.fillMaxWidth()
//            )
//            {
//                Text("Protein")
//                Text(food.protein.toString())
//            }
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier.fillMaxWidth()
//            )
//            {
//                Text("Fat")
//                Text(food.totalFat.toString())
//            }
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier.fillMaxWidth()
//            )
//            {
//                Text("Carbohydrates")
//                Text(food.carbohydrates.toString())
//            }
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier.fillMaxWidth()
//            )
//            {
//                Text("Saturated Fat")
//                Text(food.saturatedFat.toString())
//            }
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier.fillMaxWidth()
//            )
//            {
//                Text("Fiber")
//                Text(food.fiber.toString())
//            }
//        }
//    }
}

@Composable
fun AddFoodConfirm(){
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
    ){
        Text("Number of servings")
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.width(200.dp))

            Button(
                onClick = { /*TODO*/ },
            ) {
              Text("Add")
            }
        }

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun AddFoodPreview(){
    val navController = rememberNavController()
    AddFood(navController)
}