package edu.bu.nutritiontracker.data.db

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.internal.Provider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.time.LocalDate

class DbInitializer(
    private val foodProvider: Provider<FoodDao>,
    private val dailyFoodsProvider: Provider<DailyFoodsDao>
): RoomDatabase.Callback() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            populateDatabase()
        }
    }

    private suspend fun populateDatabase() {
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

        //initial DailyFoods entries
        val dailyFoodEntry1 = DailyFoods(
            foodId = 1,
            numServings = 3,
            date = LocalDate.now(),
        )
        val dailyFoodEntry2 = DailyFoods(
            foodId = 2,
            numServings = 2,
            date = LocalDate.now(),
        )

        foodProvider.get().insertFood(apple)
        foodProvider.get().insertFood(whiteRice)
        foodProvider.get().insertFood(chickenBreast)

    }

}