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

    private val _dailyFoodsWithFood = MutableStateFlow<List<DailyFoodEntryWithFood>>((emptyList()))
    private val _foodSearchResultList = MutableStateFlow<List<Food>>(emptyList())
    private val _food = MutableStateFlow<Food?>(null)

    //expose immutables
    val foodSearchResultList = _foodSearchResultList.asStateFlow()
    val food = _food.asStateFlow()
    val dailyFoodsWithFood = _dailyFoodsWithFood.asStateFlow()

    init {
        //all of these are just for testing
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
        _dailyFoodsWithFood.value.forEach { entry ->
            result["Calories"] = result.getValue("Calories") + (entry.food.calories * entry.dailyFoods.numServings)
            result["Protein"] = result.getValue("Protein") + (entry.food.protein * entry.dailyFoods.numServings)
            result["Fat"] = result.getValue("Fat") + (entry.food.totalFat * entry.dailyFoods.numServings)
            result["Carbohydrates"] = result.getValue("Carbohydrates") + (entry.food.carbohydrates * entry.dailyFoods.numServings)
            result["Saturated Fat"] = result.getValue("Saturated Fat") + (entry.food.saturatedFat * entry.dailyFoods.numServings)
            result["Fiber"] = result.getValue("Fiber") + (entry.food.fiber * entry.dailyFoods.numServings)

        }

        return result
    }

}