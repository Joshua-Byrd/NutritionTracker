package edu.bu.nutritiontracker.data

/**
 * Stores the result of JOIN between a DailyFoods entry and a Food.
 * Used to display foods eaten by date
 */

data class DailyFoodEntryWithFood(val dailyFoods: DailyFoods, val food: Food) {}