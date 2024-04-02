package edu.bu.nutritiontracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

@Entity
data class DailyFoods(
    @PrimaryKey(autoGenerate = true)
    val foodEntryId: Int,
    val foodId: Int, //foreign key to food?
    val numServings: Int,
    val date: LocalDateTime = LocalDateTime.now()
){}