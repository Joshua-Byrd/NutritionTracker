package edu.bu.nutritiontracker

import androidx.compose.ui.Alignment
import android.content.ClipData.Item
import android.graphics.Paint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.bu.nutritiontracker.data.Food
import edu.bu.nutritiontracker.util.getTestFoodMap
import edu.bu.nutritiontracker.util.sumFoods
import java.text.SimpleDateFormat
import java.util.Date
import edu.bu.nutritiontracker.components.BottomMenu
import edu.bu.nutritiontracker.components.FoodList

/**
 * Displays date, nutrition summary and list of foods eaten
 */


@Composable
fun DailyDisplay(date: Date, foodMap: Map<Food, Int>) {
    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)

    ){

        DateDisplay(date)

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Summary(foodMap)

        Spacer(
            modifier = Modifier.height(10.dp)
        )
        Text("Foods Eaten", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        FoodList(foodMap)


        BottomMenu() {}
    }


}

@Composable
fun DateDisplay(date: Date) {
    //display date
    val formattedDate = SimpleDateFormat("MM/dd/yyyy").format(date)
    Text(formattedDate, fontSize = 24.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun Summary(foodMap: Map<Food, Int>) {
    //display summaries
    Text("Summary", fontSize = 18.sp, fontWeight = FontWeight.Bold)
    val foodSummary = sumFoods(foodMap)
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




