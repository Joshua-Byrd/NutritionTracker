package edu.bu.nutritiontracker.data

import androidx.lifecycle.ViewModel
import edu.bu.nutritiontracker.util.getTestFoodList
import edu.bu.nutritiontracker.util.getTestFoodMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FoodViewModel(
    private val foodDao: FoodDao,
    private val dailyFoodsDao: DailyFoodsDao
): ViewModel() {


    //Just for testing purposes. These will eventually come from a Model

    private val _dailyFoodsMap = MutableStateFlow<Map<Food, Int>>(emptyMap())

    //This will come from foodDao after searching for specific food
    private val _foodSearchResultList = MutableStateFlow<List<Food>>(emptyList())
    private val _food = MutableStateFlow<Food?>(null)

    //expose immutables
    val dailyFoodsMap = _dailyFoodsMap.asStateFlow()
    val foodSearchResultList = _foodSearchResultList.asStateFlow()
    val food = _food.asStateFlow()

    init {
        //all of these are just for testing
        _dailyFoodsMap.value = getTestFoodMap()
        _foodSearchResultList.value = getTestFoodList()
        _food.value = Food(1,"apple", "1 - medium", 95.6, 0.5, 0.3,
            25.1, 0.0, 4.4)
    }

    /**
     * Returns a map where the keys are the nutrition data points in _foodMap,
     * and the values are the sum of all food items
     */
    fun getFoodListSummary(): Map<String, Double>{

        val result = mutableMapOf<String, Double>(
            "Calories" to 0.0,
            "Protein" to 0.0,
            "Fat" to 0.0,
            "Carbohydrates" to 0.0,
            "Saturated Fat" to 0.0,
            "Fiber" to 0.0
        )

        //Get the data point, multiply by number of servings, and add to total in result
        _dailyFoodsMap.value.forEach { entry ->
            result["Calories"] = result.getValue("Calories") + (entry.key.calories * entry.value)
            result["Protein"] = result.getValue("Protein") + (entry.key.protein * entry.value)
            result["Fat"] = result.getValue("Fat") + (entry.key.totalFat * entry.value)
            result["Carbohydrates"] = result.getValue("Carbohydrates") + (entry.key.carbohydrates * entry.value)
            result["Saturated Fat"] = result.getValue("Saturated Fat") + (entry.key.saturatedFat * entry.value)
            result["Fiber"] = result.getValue("Fiber") + (entry.key.fiber * entry.value)

        }

        return result
    }

}