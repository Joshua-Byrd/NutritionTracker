package edu.bu.nutritiontracker.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food(
    @PrimaryKey(autoGenerate = true)
    var foodId: Int = 0,
    var name: String,
    var serving: String,
    var calories: Double,
    var protein: Double,
    var totalFat: Double,
    var carbohydrates: Double,
    var saturatedFat: Double,
    var fiber: Double
) {}