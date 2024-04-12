package edu.bu.nutritiontracker.data
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.bu.nutritiontracker.data.db.DailyFoodEntryWithFood
import edu.bu.nutritiontracker.data.db.DailyFoods
import edu.bu.nutritiontracker.data.repository.DailyFoodsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

//the state of the lists of daily entries (by date and recent)
data class DailyFoodsUiState(
    val dailyFoodsWithFoodByDate: List<DailyFoodEntryWithFood> = emptyList(),
    val recentFoodEntriesList: List<DailyFoodEntryWithFood> = emptyList()
)

//the state of a food entry to be added
data class DailyFoodEntryUiState(
    val dailyFoodEntry: DailyFoods = DailyFoods(
        date = LocalDate.now(),
        foodId = 0,
        numServings = 0
    )
)

@HiltViewModel
class DailyFoodsViewModel @Inject constructor(
    private val dailyFoodsRepository: DailyFoodsRepository
):ViewModel() {

    //date of food entries to display
    private val _date: MutableStateFlow<LocalDate> = MutableStateFlow(LocalDate.now())
    val date: StateFlow<LocalDate> = _date

    //list of foods by date
    private val _dailyFoodsUiState: MutableStateFlow<DailyFoodsUiState>
    = MutableStateFlow(DailyFoodsUiState())

    val foodsUiState: StateFlow<DailyFoodsUiState> = _dailyFoodsUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        DailyFoodsUiState(),
        )

    //Food summary
    private val _foodSummary: MutableStateFlow<Map<String, Double>> = MutableStateFlow(emptyMap())
    val foodSummary: StateFlow<Map<String, Double>> = _foodSummary


    private val _dailyFoodEntryUiState: MutableStateFlow<DailyFoodEntryUiState> =
        MutableStateFlow(DailyFoodEntryUiState())

    val foodEntryUiState: StateFlow<DailyFoodEntryUiState> = _dailyFoodEntryUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        DailyFoodEntryUiState(),
    )

    init {
        //all entries by date
        viewModelScope.launch {
            dailyFoodsRepository.getDailyFoodsWithFoodByDate(date.value).collect {
                entries ->
                _dailyFoodsUiState.update {
                    it.copy(dailyFoodsWithFoodByDate = entries)
                }
            }
        }

        //recent entries
        viewModelScope.launch {
            dailyFoodsRepository.getRecentFoodsList().collect {
                    entries ->
                _dailyFoodsUiState.update {
                    it.copy(recentFoodEntriesList = entries)
                }
            }
        }

        //food summary
        viewModelScope.launch {
            foodsUiState.collect { state ->
                _foodSummary.value = dailyFoodsRepository.summarizeFoodList(state.dailyFoodsWithFoodByDate)
            }
        }
    }

    fun updateDate(newDate: LocalDate) {
        _date.value = newDate
    }

    fun deleteDailyFoodEntry(entry:DailyFoods) {
        viewModelScope.launch {
            dailyFoodsRepository.deleteDailyFoods(entry)
        }
    }

    fun addDailyFoodEntry(
        date: LocalDate,
        foodId: Int,
        numServings: Int
    ){
        viewModelScope.launch {
            val newFood = DailyFoods(foodId = foodId, numServings = numServings, date = date)
            dailyFoodsRepository.insertDailyFoods(newFood)
        }
    }
}
