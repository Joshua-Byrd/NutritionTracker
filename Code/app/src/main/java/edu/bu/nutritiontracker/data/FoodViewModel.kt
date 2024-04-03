package edu.bu.nutritiontracker.data

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.bu.nutritiontracker.data.repository.DailyFoodsRepository
import edu.bu.nutritiontracker.data.repository.FoodRepository
import edu.bu.nutritiontracker.util.getTestFoodList
import edu.bu.nutritiontracker.util.getTestFoodMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    foodRepository: FoodRepository,
    dailyFoodsRepository: DailyFoodsRepository
    ): ViewModel() {

    private val _dailyFoodsWithFood = MutableStateFlow<List<DailyFoodEntryWithFood>>(emptyList())
    private val _recentFoodsList = MutableStateFlow<List<DailyFoodEntryWithFood>>(emptyList())
    private val _foodSearchResultList = MutableStateFlow<List<Food>>(emptyList())
    private val _food = MutableStateFlow<Food?>(null)
    private val _foodSummary = MutableStateFlow<Map<String, Double>>(emptyMap())

    //expose immutables
    val dailyFoodsWithFood = _dailyFoodsWithFood.asStateFlow()
    val recentFoodsList = _recentFoodsList.asStateFlow()
    val foodSearchResultList = _foodSearchResultList.asStateFlow()
    val food = _food.asStateFlow()
    val foodSummary = _foodSummary.asStateFlow()



    init {
        //all of these are just for testing
        _foodSearchResultList.value = getTestFoodList()
        _food.value = Food(1,"apple", "1 - medium", 95.6, 0.5, 0.3,
            25.1, 0.0, 4.4)
    }




}