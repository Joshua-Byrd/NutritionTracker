package edu.bu.nutritiontracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DailyFoods(
    @PrimaryKey(autoGenerate = true)
    val foodEntryId: Int,
    val foodId: Int, //foreign key to food?
    val date: String //date or text for SQLite?
){}