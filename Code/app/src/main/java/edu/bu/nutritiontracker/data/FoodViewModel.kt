package edu.bu.nutritiontracker.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.bu.nutritiontracker.data.db.Food
import edu.bu.nutritiontracker.data.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FoodSearchState (
    val foodSearchResults: List<Food> = emptyList()
)

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val foodRepository: FoodRepository,
    ): ViewModel() {

        private val _name: MutableStateFlow<String> = MutableStateFlow("")
    val name: StateFlow<String> get() = _name


        private val _foodUiState: MutableStateFlow<FoodSearchState>
        = MutableStateFlow(FoodSearchState())

        val foodUiState: StateFlow<FoodSearchState> = _foodUiState.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            FoodSearchState()
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
    fun getFoodById(foodId: Int): Flow<Food> {
        return foodRepository.getFoodById(foodId)
    }

    fun getFoodListByName(foodName: String): Flow<List<Food>> {
        return foodRepository.getFoodByName(foodName)
    }

}