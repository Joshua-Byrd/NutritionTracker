package edu.bu.nutritiontracker.data.repository

import edu.bu.nutritiontracker.data.DailyFoodEntryWithFood
import edu.bu.nutritiontracker.data.DailyFoods
import edu.bu.nutritiontracker.data.DailyFoodsDao
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

class DailyFoodsRepository(private val dailyFoodsDao: DailyFoodsDao) {

    fun insertDailyFoods(entry: DailyFoods) {
        dailyFoodsDao.insertDailyFoods(entry)
    }

    fun deleteDailyFoods(entry: DailyFoods) {
        dailyFoodsDao.deleteDailyFoods(entry)
    }

    fun getDailyFoodsWithFoodByDate(searchDate: LocalDateTime): Flow<List<DailyFoodEntryWithFood>> {
        return dailyFoodsDao.getDailyFoodsWithFoodByDate(searchDate)
    }

    fun getRecentFoodsList(): Flow<List<DailyFoodEntryWithFood>> {
        return dailyFoodsDao.getRecentFoodsList()
    }
}