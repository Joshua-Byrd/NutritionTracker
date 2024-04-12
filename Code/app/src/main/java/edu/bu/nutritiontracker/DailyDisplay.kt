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
import edu.bu.nutritiontracker.data.DailyFoodsViewModel
import edu.bu.nutritiontracker.data.FoodViewModel
import java.lang.Exception
import java.time.LocalDate
import java.time.LocalDateTime


/**
 * Displays date, nutrition summary and list of foods eaten
 */

@Composable
fun DailyDisplay(
    navController: NavController
) {
    ScaffoldExample()
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
            Text(entry.key)
            val formattedValue = String.format("%.1f", entry.value)
            Text(formattedValue)
        }
    }
}


//This is here just to having a UI while making sure the database installs
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample(viewModel: DailyFoodsViewModel = hiltViewModel()) {
    val date by viewModel.date.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text ="$date",
                        modifier = Modifier,
                        textAlign = TextAlign.Center
                    //TODO make this clickable with calendar; update date
                    )
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(onClick = { /*goto search page*/ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
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

            FoodList()

        }
    }
}


//@Preview(
//    showBackground = true,
//    showSystemUi = true)
//@Composable
//fun DailyDisplayPreview() {
//    val currentDate = Date()
//    val navController = rememberNavController()
//    DailyDisplay(navController = navController, currentDate)
//}




