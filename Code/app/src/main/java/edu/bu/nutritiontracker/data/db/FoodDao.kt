package edu.bu.nutritiontracker.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Insert
    suspend fun insertFood(food: Food)

    @Delete
    suspend fun deleteFood(food: Food)

    @Query("SELECT * FROM FOOD WHERE foodId = :searchId")
    fun getFoodById(searchId: Int): Flow<Food>

    @Query("SELECT * FROM Food WHERE name LIKE '%' || :searchQuery || '%' ")
    fun getFoodByName(searchQuery: String): Flow<List<Food>>

}