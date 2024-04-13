package edu.bu.nutritiontracker.util
import android.os.Build
import androidx.annotation.RequiresApi
import edu.bu.nutritiontracker.data.db.Food
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset

/**
 * Provides utility functions to other classes/Activities
 */

//Food objects to work with
val apple = Food(1,"apple", "",95.6, 0.5, 0.3,
    25.1, 0.0, 4.4)
val applePie = Food(2,"apple pie", "",375.0, 3.1, 15.9,
    52.7, 6.0, 2.0)
val appleDumpling = Food(3,"apple dumpling", "", 621.0, 5.1, 26.8,
    92.3, 11.2, 3.2)

val banana = Food(4,"banana", "",105.0, 1.3, 0.4,
    27.0, 0.0, 3.1)
val oats = Food(5, "oats", "",150.0, 5.3, 2.6,
    27.4, 0.4, 4.1)
val chickenBreast = Food(6, "chicken breast", "",42.0, 8.6,
    0.9,0.0, 0.3, 0.0)
val broccoli = Food(7, "broccoli", "",105.0, 1.3, 0.4,
    27.0, 0.0, 3.1)
val whiteRice = Food(8, "white rice", "", 110.0, 2.5,
    0.5, 22.2, 0.0, 1.2)

fun getTestFoodList(): List<Food> {
    return listOf(apple, applePie, appleDumpling)
}

/**
 * Returns a map of foods to servings to use in testing
 */
fun getTestFoodMap(): Map<Food, Int> {
    return mapOf(
        apple to 1,
        banana to 1,
        oats to 2,
        chickenBreast to 6,
        broccoli to 2,
        whiteRice to 3)
}

fun convertMillisToLocalDate(millis: Long?) : LocalDate? {
    return millis?.let {
        Instant
        .ofEpochMilli(it)
        .atZone(ZoneOffset.UTC)
        .toLocalDate()
    }
}



