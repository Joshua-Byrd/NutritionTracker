package edu.bu.nutritiontracker.data

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import edu.bu.nutritiontracker.util.getTestFoodMap

class FoodsViewModel: ViewModel() {
    //Just for testing purposes. This will eventually come from the database
    private val _foodMap = getTestFoodMap().toMutableMap()

    val foodMap = _foodMap.toMap()

}