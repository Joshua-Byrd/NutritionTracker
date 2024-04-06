package edu.bu.nutritiontracker.data.repository

import edu.bu.nutritiontracker.data.db.Food
import edu.bu.nutritiontracker.data.db.FoodDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodRepository @Inject constructor(private val foodDao: FoodDao) {

    suspend fun insertFood(food: Food) {
        foodDao.insertFood(food)
    }

    suspend fun deleteFood(food: Food) {
        foodDao.deleteFood(food)
    }

    fun getFoodById(searchId: Int): Flow<Food> {
        return foodDao.getFoodById(searchId)
    }

    fun getFoodByName(searchQuery: String): Flow<List<Food>> {
        return foodDao.getFoodByName(searchQuery)
    }
}
