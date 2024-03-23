package edu.bu.nutritiontracker.util

import edu.bu.nutritiontracker.data.Food

/**
 * Provides utility functions to other classes/Activities
 */


/**
 * Accepts a list of Food objects, sums each of their constituent data
 * points (calories, macros) and returns a map of the sums
 */
fun sumFoods(foodList: List<Food>): Map<String, Int>{
    val foodMap = mutableMapOf<String, Int>(
        "calories" to 0,
        "protein" to 0,
        "totalFat" to 0,
        "carbohydrates" to 0,
        "saturatedFat" to 0,
        "fiber" to 0
    )

    for (food in foodList) {
        foodMap["calories"] = foodMap.getValue("calories") + food.calories
        foodMap["protein"] = foodMap.getValue("protein") + food.protein
        foodMap["totalFat"] = foodMap.getValue("totalFat") + food.totalFat
        foodMap["carbohydrates"] = foodMap.getValue("carbohydrates") + food.carbohydrates
        foodMap["saturatedFat"] = foodMap.getValue("saturatedFat") + food.saturatedFat
        foodMap["fiber"] = foodMap.getValue("fiber") + food.fiber
    }

    return foodMap
}