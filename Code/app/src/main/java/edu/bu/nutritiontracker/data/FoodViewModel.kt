package edu.bu.nutritiontracker.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.bu.nutritiontracker.data.db.DailyFoodEntryWithFood
import edu.bu.nutritiontracker.data.db.DailyFoods
import edu.bu.nutritiontracker.data.db.Food
import edu.bu.nutritiontracker.data.db.NutritionDatabase
import edu.bu.nutritiontracker.data.repository.DailyFoodsRepository
import edu.bu.nutritiontracker.data.repository.FoodRepository
import edu.bu.nutritiontracker.util.getTestFoodList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

data class foodSearchState (
    val foodSearchResults: List<Food> = emptyList()
)

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val foodRepository: FoodRepository,
    ): ViewModel() {
        private val _name: MutableStateFlow<String> = MutableStateFlow("")
        val name: StateFlow<String> = _name

        private val _foodUiState: MutableStateFlow<foodSearchState>
        = MutableStateFlow(foodSearchState())

        val foodUiState: StateFlow<foodSearchState> = _foodUiState.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            foodSearchState()
        )
    init {
        viewModelScope.launch{
            foodRepository.getFoodByName(name.value).collect {
                foods -> _foodUiState.update {
                    it.copy(foodSearchResults = foods)
            }
            }
        }

    }




}