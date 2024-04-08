package edu.bu.nutritiontracker.data.db

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@Database(
    entities = [(Food::class), (DailyFoods:: class)],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateTimeConverter::class)
abstract class NutritionDatabase: RoomDatabase() {

    abstract fun foodDao(): FoodDao
    abstract fun dailyFoodsDao(): DailyFoodsDao

}