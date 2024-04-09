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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.util.Date
import edu.bu.nutritiontracker.components.BottomMenu
import edu.bu.nutritiontracker.components.FoodList
import edu.bu.nutritiontracker.data.FoodViewModel
import java.time.LocalDate
import java.time.LocalDateTime


/**
 * Displays date, nutrition summary and list of foods eaten
 */

@Composable
fun DailyDisplay(
    navController: NavController,
    date: Date
) {
    ScaffoldExample(LocalDate.now())

//    Column (
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxSize()
//
//    ){
//
//        DateDisplay(date)
//
//        Spacer(
//            modifier = Modifier.height(10.dp)
//        )
//
//        Summary()
//
//        Spacer(
//            modifier = Modifier.height(10.dp)
//        )
//        Text("Foods Eaten", fontSize = 18.sp, fontWeight = FontWeight.Bold)
//        FoodList()
//
//
//        BottomMenu(navController){}
//        }



}

@SuppressLint("SimpleDateFormat")
@Composable
fun DateDisplay(date: LocalDateTime) {
    //display date
    val formattedDate = SimpleDateFormat("MM/dd/yyyy").format(date)
    Text(formattedDate, fontSize = 24.sp, fontWeight = FontWeight.Bold)
}

//@Composable
//fun Summary(viewModel: FoodViewModel = hiltViewModel()) {
//    //display summaries
//    Text("Summary", fontSize = 18.sp, fontWeight = FontWeight.Bold)
//    val foodSummary = viewModel.foodSummary.collectAsState()
//    foodSummary.value.forEach {
//            entry ->
//        Row (
//            horizontalArrangement = Arrangement.SpaceBetween,
//            modifier = Modifier.fillMaxWidth()
//        ){
//            Text(entry.key)
//            val formattedValue = String.format("%.1f", entry.value)
//            Text(formattedValue)
//        }
//    }
//}


//This is here just to having a UI while making sure the database installs
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample(date: LocalDate) {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("$date")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button $presses times.
                """.trimIndent(),
            )
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




