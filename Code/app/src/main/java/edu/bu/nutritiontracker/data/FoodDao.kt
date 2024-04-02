package edu.bu.nutritiontracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    //Should these be asynchronous using suspend?

    @Insert
    fun insertFood(food: Food)

    @Delete
    fun deleteFood(food: Food)

    @Query("SELECT * FROM Food WHERE name LIKE '%' || :searchQuery || '%' ")
    fun getFoodByName(searchQuery: String): Flow<List<Food>>



}