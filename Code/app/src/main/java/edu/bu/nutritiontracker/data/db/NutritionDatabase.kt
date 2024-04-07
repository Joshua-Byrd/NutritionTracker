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

    private class DatabaseCallback(private val context: Context) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database.foodDao(), database.dailyFoodsDao())
                }
            }
        }

        private fun populateDatabase(foodDao: FoodDao, dailyFoodsDao: DailyFoodsDao) {

        }
    }

    private suspend fun populateDatabase(foodDao: FoodDao, dailyFoodsDao: DailyFoodsDao) {
        //initial foods
        val apple = Food(
            name = "Apple, fresh",
            serving = "1 medium",
            calories = 95.0,
            protein = 0.5,
            totalFat = 0.3,
            carbohydrates = 25.1,
            saturatedFat = 0.0,
            fiber = 4.4
        )
        val whiteRice = Food(
            name = "Rice, white, steamed",
            serving = "1 cup",
            calories = 210.0,
            protein = 4.2,
            totalFat = 0.4,
            carbohydrates = 44.6,
            saturatedFat = 0.5,
            fiber = 0.3
        )

        val chickenBreast = Food(
            name = "Chicken breast, boneless, skinless",
            serving = "1 oz",
            calories = 42.0,
            protein = 8.6,
            totalFat = 0.9,
            carbohydrates = 0.0,
            saturatedFat = 0.3,
            fiber = 0.0
        )
        foodDao.insertFood(apple)
        foodDao.insertFood(whiteRice)
        foodDao.insertFood(chickenBreast)


        //initial DailyFoods entries
        val dailyFoodEntry1 = DailyFoods(
            foodId = 1,
            numServings = 3,
            date = LocalDateTime.now(),
        )
        val dailyFoodEntry2 = DailyFoods(
            foodId = 2,
            numServings = 2,
            date = LocalDateTime.now(),
        )
        dailyFoodsDao.insertDailyFoods(dailyFoodEntry1)
        dailyFoodsDao.insertDailyFoods(dailyFoodEntry2)

    }
}