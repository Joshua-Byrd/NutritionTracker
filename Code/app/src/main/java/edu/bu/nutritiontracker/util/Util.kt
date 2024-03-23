package edu.bu.nutritiontracker.util
import edu.bu.nutritiontracker.data.Food

/**
 * Provides utility functions to other classes/Activities
 */

/**
 * Returns a map of foods to servings to use in testing
 */
fun getTestFoodMap(): Map<Food, Int> {
    val apple = Food("apple - 1 medium", 95.6, 0.5, 0.3,
        25.1, 0.0, 4.4)
    val banana = Food("banana - 1 medium", 105.0, 1.3, 0.4,
        27.0, 0.0, 3.1)
    val oats = Food("oats - 1/2 cup", 150.0, 5.3, 2.6,
        27.4, 0.4, 4.1)
    val chickenBreast = Food("chicken breast - 1oz", 42.0, 8.6,
        0.9,0.0, 0.3, 0.0)
    val broccoli = Food("broccoli - 1/2 cup", 105.0, 1.3, 0.4,
        27.0, 0.0, 3.1)
    val whiteRice = Food("white rice - 1/2 cup", 110.0, 2.5,
        0.5, 22.2, 0.0, 1.2)

    return mapOf(
        apple to 1,
        banana to 1,
        oats to 2,
        chickenBreast to 6,
        broccoli to 2,
        whiteRice to 3)
}

/**
 * Accepts a map where the keys are Food items and the values are the number of servings,
 * sums each of their constituent data points (calories, macros) and returns a map of the sums
 * for display purposes.
 */
fun sumFoods(foodMap: Map<Food, Int>): Map<String, Double>{

    val result = mutableMapOf<String, Double>(
        "Calories" to 0.0,
        "Protein" to 0.0,
        "Fat" to 0.0,
        "Carbohydrates" to 0.0,
        "Saturated Fat" to 0.0,
        "Fiber" to 0.0
    )

    //Get the data point, multiply by number of servings, and add to total in result
    foodMap.forEach {entry ->
        result["Calories"] = result.getValue("Calories") + (entry.key.calories * entry.value)
        result["Protein"] = result.getValue("Protein") + (entry.key.protein * entry.value)
        result["Fat"] = result.getValue("Fat") + (entry.key.totalFat * entry.value)
        result["Carbohydrates"] = result.getValue("Carbohydrates") + (entry.key.carbohydrates * entry.value)
        result["Saturated Fat"] = result.getValue("Saturated Fat") + (entry.key.saturatedFat * entry.value)
        result["Fiber"] = result.getValue("Fiber") + (entry.key.fiber * entry.value)

    }

    return result
}

