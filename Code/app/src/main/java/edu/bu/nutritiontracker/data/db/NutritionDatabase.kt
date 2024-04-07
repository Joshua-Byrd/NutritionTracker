package edu.bu.nutritiontracker.data.db

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [(Food::class), (DailyFoods:: class)],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateTimeConverter::class)
abstract class NutritionDatabase: RoomDatabase() {

    abstract fun foodDao(): FoodDao
    abstract fun dailyFoodsDao(): DailyFoodsDao

    companion object {
        @Volatile
        private var INSTANCE: NutritionDatabase? = null

        fun getDatabase(context: Context): NutritionDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NutritionDatabase::class.java,
                    "nutrition_db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }


    }
}