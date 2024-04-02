package edu.bu.nutritiontracker.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo
import java.time.LocalDateTime
import java.util.Date

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
    val foodEntryId: Int,
    val foodId: Int, //foreign key to food
    val numServings: Int,
    val date: LocalDateTime = LocalDateTime.now()
){}