package edu.bu.nutritiontracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food(
    @PrimaryKey(autoGenerate = true)
    val foodId: Int,
    val name: String,
    val calories: Double,
    val protein: Double,
    val totalFat: Double,
    val carbohydrates: Double,
    val saturatedFat: Double,
    val fiber: Double
) {}