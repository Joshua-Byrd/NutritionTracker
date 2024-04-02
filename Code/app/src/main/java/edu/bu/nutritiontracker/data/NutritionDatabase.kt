package edu.bu.nutritiontracker.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [(FoodDao::class), (DailyFoodsDao:: class)],
    version = 1
)
abstract class NutritionDatabase: RoomDatabase() {

    abstract fun foodDao():  FoodDao
    abstract fun dailyFoodsDao(): DailyFoodsDao

}