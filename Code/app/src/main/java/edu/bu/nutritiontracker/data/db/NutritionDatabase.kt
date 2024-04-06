package edu.bu.nutritiontracker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.bu.nutritiontracker.util.DateTimeConverter

@Database(
    entities = [(FoodDao::class), (DailyFoodsDao:: class)],
    version = 1
)
@TypeConverters(DateTimeConverter::class)
abstract class NutritionDatabase: RoomDatabase() {

    abstract fun foodDao(): FoodDao
    abstract fun dailyFoodsDao(): DailyFoodsDao

}