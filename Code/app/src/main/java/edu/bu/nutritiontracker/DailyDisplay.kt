package edu.bu.nutritiontracker

import android.annotation.SuppressLint
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.util.Date
import edu.bu.nutritiontracker.components.BottomMenu
import edu.bu.nutritiontracker.components.FoodList
import edu.bu.nutritiontracker.data.FoodViewModel

/**
 * Displays date, nutrition summary and list of foods eaten
 */
@Composable
fun DailyDisplay(
    navController: NavController,
    date: Date,
    viewModel: FoodViewModel = FoodViewModel()) {

    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()

    ){

        DateDisplay(date)

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Summary()

        Spacer(
            modifier = Modifier.height(10.dp)
        )
        Text("Foods Eaten", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        FoodList()


        BottomMenu(navController){}
    }


}

@SuppressLint("SimpleDateFormat")
@Composable
fun DateDisplay(date: Date) {
    //display date
    val formattedDate = SimpleDateFormat("MM/dd/yyyy").format(date)
    Text(formattedDate, fontSize = 24.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun Summary(viewModel: FoodViewModel = FoodViewModel()) {
    //display summaries
    Text("Summary", fontSize = 18.sp, fontWeight = FontWeight.Bold)
    val foodSummary = viewModel.getFoodListSummary()
    foodSummary.forEach {
            entry ->
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(entry.key)
            val formattedValue = String.format("%.1f", entry.value)
            Text(formattedValue)
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun DailyDisplayPreview() {
    val currentDate = Date()
    val navController = rememberNavController()
    DailyDisplay(navController = navController, currentDate)
}




