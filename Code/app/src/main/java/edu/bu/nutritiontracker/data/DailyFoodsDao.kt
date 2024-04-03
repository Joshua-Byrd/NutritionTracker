package edu.bu.nutritiontracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface DailyFoodsDao {

    @Insert
    fun insertDailyFoods(entry:DailyFoods)

    @Delete
    fun deleteDailyFoods(entry:DailyFoods)

    @Query ("SELECT * FROM DailyFoods WHERE date = :searchDate")
    fun getDailyFoodsWithFoodByDate(searchDate: LocalDateTime): Flow<List<DailyFoodEntryWithFood>>

    @Query("SELECT * FROM DailyFoods ORDER BY date DESC LIMIT 20")
    fun getRecentFoodsList(): Flow<List<DailyFoods>>
}