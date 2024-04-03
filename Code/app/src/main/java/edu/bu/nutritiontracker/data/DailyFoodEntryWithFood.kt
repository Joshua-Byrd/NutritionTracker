package edu.bu.nutritiontracker.data

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Stores the result of JOIN between a DailyFoods entry and a Food.
 * Used to display foods eaten by date
 */

data class DailyFoodEntryWithFood(
    @Embedded val dailyFoods: DailyFoods,
    @Relation(
        parentColumn = "foodId",
        entityColumn = "foodId"
    )
    val food: Food
) {}