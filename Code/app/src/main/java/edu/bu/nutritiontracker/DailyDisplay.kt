package edu.bu.nutritiontracker


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.bu.nutritiontracker.data.DailyFoodsViewModel
import edu.bu.nutritiontracker.data.db.DailyFoodEntryWithFood
import edu.bu.nutritiontracker.util.convertMillisToLocalDate
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale


/**
 * Displays date, nutrition summary and list of foods eaten
 */

@Composable
fun DailyDisplay(
    navController: NavController
) {
    DailyDisplayScaffold(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyDisplayScaffold(
    navController:NavController,
    viewModel: DailyFoodsViewModel = hiltViewModel()
) {

    //DatePicker state
    var openDialog = remember { mutableStateOf(false) }
    val date by viewModel.date.collectAsState()
    val dateState = rememberDatePickerState()
    val formattedDate = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(

                        text = formattedDate,
                        modifier = Modifier
                            .clickable { openDialog.value = true }
                            .fillMaxWidth(),

                        textAlign = TextAlign.Center
                    //TODO finish the date picker
                    )
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("foodSearch") }) {
//                Icon(Icons.Default.Add, contentDescription = "Add")
                Text(
                    text = "Add Food",
                    modifier = Modifier.padding(8.dp),
                    color = Color(0xFF4C662B)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(8.dp)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text ="Summary",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center)
            Summary()

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text ="Foods Eaten",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center)

            DailyFoodList(navController)

        }
    }

    if (openDialog.value) {
        //TODO finish the date picker
        DatePickerDialog(
            onDismissRequest = { openDialog.value = false },
            confirmButton = {
                Button(
                    onClick = {
                        convertMillisToLocalDate(dateState.selectedDateMillis)?.let {
                            viewModel.updateDate(
                                it
                            )
                        }
                        openDialog.value = false }
                ) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = { openDialog.value = false }
                ) {
                    Text(text = "Cancel")
                }
            }) {
            DatePicker(
                state = dateState,
                showModeToggle = true
            )
        }
    }
}




@Composable
fun Summary(viewModel: DailyFoodsViewModel = hiltViewModel()) {

    val dailyFoodsUiState by viewModel.foodsUiState.collectAsState()
    val foodSummary by viewModel.foodSummary.collectAsState()

    //display summaries
    foodSummary.forEach {
            entry ->
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ){
            if (entry.key == "Calories") {
                Text(entry.key)
                val formattedValue = String.format("%.1f", entry.value)
                Text(text = "$formattedValue cals")
            } else {
                Text(entry.key)
                val formattedValue = String.format("%.1f", entry.value)
                Text(text = "$formattedValue     g")
            }
        }
    }
}


@Composable
fun DailyFoodList(navController: NavController, viewModel: DailyFoodsViewModel = hiltViewModel()) {

    val dailyFoodsUiState by viewModel.foodsUiState.collectAsState()
    val date by viewModel.date.collectAsState()

    val foodList = dailyFoodsUiState.dailyFoodsWithFoodByDate

    if (foodList.isEmpty()) {
        Text("Nothing yet!")
    } else {
        LazyColumn(
        ) {
            foodList.forEach { entry ->
                item {
                    ClickableDailyListFoodEntryWithFood(
                        entry = entry,
                        onDeleteClicked = {
                            viewModel.deleteDailyFoodEntry(entry.dailyFoods)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ClickableDailyListFoodEntryWithFood(
    entry: DailyFoodEntryWithFood,
    onDeleteClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color(0xFFBFCBAD), shape = RoundedCornerShape(4.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .background(Color(0xFFBFCBAD))
        ) {

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${entry.food.name}",
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "${entry.dailyFoods.numServings} serving(s)",
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                    fontSize = 12.sp
                )
            }

            val formattedCals = String.format("%.1f", (entry.food.calories * entry.dailyFoods.numServings))
            Text("$formattedCals cals")

            IconButton(
                onClick = onDeleteClicked,
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Delete",
                    modifier = Modifier
                )
            }
        }
    }
}





