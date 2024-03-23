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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import edu.bu.nutritiontracker.data.Food
import edu.bu.nutritiontracker.util.getTestFoodMap
import edu.bu.nutritiontracker.util.sumFoods
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Displays date, nutrition summary and list of foods eaten
 *  ---NEEDS TO ACCEPT A DATE TO DISPLAY (Date class?)---
 */
@Composable
fun DailyDisplay(date: Date, foodMap: Map<Food, Int>) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        //display date
        val formattedDate = SimpleDateFormat("MM/dd/yyyy").format(date)
        Text(formattedDate)

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        //display summaries
        Text("Summary")
        val foodSummary = sumFoods(foodMap)
        foodSummary.forEach {
            entry ->
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(entry.key)
                Text("${entry.value}")
            }
        }
        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text("Foods")
        //display full list of foods eaten
        LazyColumn {

            foodMap.forEach { entry ->
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(entry.key.name)
                        Text("${entry.value} serving(s)",
                            modifier = Modifier
                                .widthIn(min = 48.dp),
                            textAlign = TextAlign.Start
                        )
                        Text("${entry.key.calories * entry.value}cals")
                    }
                }
            }
        }


        //display bottom menu (just "add food" food now

    }
}

