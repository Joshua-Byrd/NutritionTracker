package edu.bu.nutritiontracker.data.repository

import edu.bu.nutritiontracker.data.db.DailyFoodEntryWithFood
import edu.bu.nutritiontracker.data.db.DailyFoods
import edu.bu.nutritiontracker.data.db.DailyFoodsDao
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import javax.inject.Inject


class DailyFoodsRepository @Inject constructor(private val dailyFoodsDao: DailyFoodsDao) {

    suspend fun insertDailyFoods(entry: DailyFoods) {
        dailyFoodsDao.insertDailyFoods(entry)
    }

    suspend fun deleteDailyFoods(entry: DailyFoods) {
        dailyFoodsDao.deleteDailyFoods(entry)
    }

    fun getDailyFoodsWithFoodByDate(searchDate: LocalDateTime): Flow<List<DailyFoodEntryWithFood>> {
        return dailyFoodsDao.getDailyFoodsWithFoodByDate(searchDate)
    }

    fun getRecentFoodsList(): Flow<List<DailyFoodEntryWithFood>> {
        return dailyFoodsDao.getRecentFoodsList()
    }

    fun getFoodListSummary(dailyFoodsWithFood: List<DailyFoodEntryWithFood>): Map<String, Double>{

        val result = mutableMapOf<String, Double>(
            "Calories" to 0.0,
            "Protein" to 0.0,
            "Fat" to 0.0,
            "Carbohydrates" to 0.0,
            "Saturated Fat" to 0.0,
            "Fiber" to 0.0
        )

        //Get the data point, multiply by number of servings, and add to total in result
        dailyFoodsWithFood.forEach { entry ->
            result["Calories"] = result.getValue("Calories") +
                    (entry.food.calories * entry.dailyFoods.numServings)
            result["Protein"] = result.getValue("Protein") +
                    (entry.food.protein * entry.dailyFoods.numServings)
            result["Fat"] = result.getValue("Fat") +
                    (entry.food.totalFat * entry.dailyFoods.numServings)
            result["Carbohydrates"] = result.getValue("Carbohydrates") +
                    (entry.food.carbohydrates * entry.dailyFoods.numServings)
            result["Saturated Fat"] = result.getValue("Saturated Fat") +
                    (entry.food.saturatedFat * entry.dailyFoods.numServings)
            result["Fiber"] = result.getValue("Fiber") +
                    (entry.food.fiber * entry.dailyFoods.numServings)
        }

        return result
    }
}