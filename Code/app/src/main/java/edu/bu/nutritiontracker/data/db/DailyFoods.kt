package edu.bu.nutritiontracker.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Food::class,
            parentColumns = ["foodId"],
            childColumns = ["foodId"]
        )
    ]
)

data class DailyFoods(
    @PrimaryKey(autoGenerate = true)
    var foodEntryId: Int = 0,
    var foodId: Int, //foreign key to food
    var numServings: Int,
    var date: LocalDateTime = LocalDateTime.now()
){}