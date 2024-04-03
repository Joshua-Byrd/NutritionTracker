package edu.bu.nutritiontracker.data.repository

import edu.bu.nutritiontracker.data.Food
import edu.bu.nutritiontracker.data.FoodDao
import kotlinx.coroutines.flow.Flow

class FoodRepository(private val foodDao: FoodDao) {

    fun insertFood(food: Food) {
        foodDao.insertFood(food)
    }

    fun deleteFood(food: Food) {
        foodDao.deleteFood(food)
    }

    fun getFoodById(searchId: Int): Flow<Food> {
        return foodDao.getFoodById(searchId)
    }

    fun getFoodByName(searchQuery: String): Flow<List<Food>> {
        return foodDao.getFoodByName(searchQuery)
    }
}
