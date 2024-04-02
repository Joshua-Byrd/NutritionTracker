package edu.bu.nutritiontracker.data

data class Food(
    val foodId: Int,
    val name: String,
    val calories: Double,
    val protein: Double,
    val totalFat: Double,
    val carbohydrates: Double,
    val saturatedFat: Double,
    val fiber: Double
) {}