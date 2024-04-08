package edu.bu.nutritiontracker.data

import dagger.hilt.android.lifecycle.HiltViewModel
import edu.bu.nutritiontracker.data.db.DailyFoodEntryWithFood
import edu.bu.nutritiontracker.data.repository.DailyFoodsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DailyFoodsViewModel @Inject constructor(
    private val dailyFoodsRepository: DailyFoodsRepository
) {

    private val _dailyFoodsWithFood = MutableStateFlow<List<DailyFoodEntryWithFood>>(emptyList())
    private val _recentFoodsList = MutableStateFlow<List<DailyFoodEntryWithFood>>(emptyList())
    private val _foodSummary = MutableStateFlow<Map<String, Double>>(emptyMap())

    val dailyFoodsWithFood = _dailyFoodsWithFood.asStateFlow()
    val recentFoodsList = _recentFoodsList.asStateFlow()
    val foodSummary = _foodSummary.asStateFlow()
}