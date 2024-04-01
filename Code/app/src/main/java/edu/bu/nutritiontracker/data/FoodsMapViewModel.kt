package edu.bu.nutritiontracker.data

import androidx.lifecycle.ViewModel
import edu.bu.nutritiontracker.util.getTestFoodList
import edu.bu.nutritiontracker.util.getTestFoodMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FoodsMapViewModel: ViewModel() {

    //Just for testing purposes. These will eventually come from the database
    //this is a map of food items to their respective number of servings for display
    private val _foodMap = MutableStateFlow<Map<Food, Int>>(emptyMap())
    private val _foodList = MutableStateFlow<List<Food>>(emptyList())

    val foodMap = _foodMap.asStateFlow()
    val foodList = _foodList.asStateFlow()

    init {
        _foodMap.value = getTestFoodMap()
        _foodList.value = getTestFoodList()
    }

    /**
     * Returns a map where the keys are the nutrition data points in _foodMap,
     * and the values are the sum of all food items
     */
    fun getSumOfFoodMap(): Map<String, Double>{

        val result = mutableMapOf<String, Double>(
            "Calories" to 0.0,
            "Protein" to 0.0,
            "Fat" to 0.0,
            "Carbohydrates" to 0.0,
            "Saturated Fat" to 0.0,
            "Fiber" to 0.0
        )

        //Get the data point, multiply by number of servings, and add to total in result
        _foodMap.value.forEach {entry ->
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