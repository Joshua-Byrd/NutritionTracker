package edu.bu.nutritiontracker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.bu.nutritiontracker.data.Food
import edu.bu.nutritiontracker.components.BottomMenu
import edu.bu.nutritiontracker.components.FoodList

@Composable
fun FoodSearch(recentFoodList: Map<Food, Int>) {
    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
    ){

        Text("Search for Foods", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        FoodSearchBar()

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text("Recent Foods", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        FoodList(foodMap = recentFoodList)

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        BottomMenu {

        }

    }
}

@Composable
fun FoodSearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {})

    Button(
        onClick = { /*TODO*/ }) {
        Text("Search")
    }
}

