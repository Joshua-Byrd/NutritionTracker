package edu.bu.nutritiontracker.data

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.bu.nutritiontracker.data.db.DailyFoodEntryWithFood
import edu.bu.nutritiontracker.data.db.Food
import edu.bu.nutritiontracker.data.db.NutritionDatabase
import edu.bu.nutritiontracker.data.repository.DailyFoodsRepository
import edu.bu.nutritiontracker.data.repository.FoodRepository
import edu.bu.nutritiontracker.util.getTestFoodList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class FoodViewModel @Inject constructor(
    private val foodRepository: FoodRepository,
    private val dailyFoodsRepository: DailyFoodsRepository
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
    }




}