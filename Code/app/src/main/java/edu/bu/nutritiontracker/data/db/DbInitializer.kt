package edu.bu.nutritiontracker.data.db

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import javax.inject.Provider

class DbInitializer(
    context: Context,
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

    /**
     * Prepopulates database with data from foods.csv
     */
    private suspend fun populateDatabase() {
        try {
            val file = File("foods.csv")
            val reader = file.bufferedReader()
            val csvReader = CSVReader(reader)

            var record: Array<String>?

            while (csvReader.readNext().also { record = it } != null) {
                println("record: $record")
                val food = Food(
                    name = record!![0],
                    serving = record!![1],
                    calories = record!![2].toDouble(),
                    protein = record!![3].toDouble(),
                    totalFat = record!![4].toDouble(),
                    carbohydrates = record!![5].toDouble(),
                    saturatedFat = record!![6].toDouble(),
                    fiber = record!![7].toDouble()
                )
                println("food: $food")
                foodProvider.get().insertFood(food)
            }
        } catch (e: Exception) {
            Log.e("DbInitializer", "Error populating database: ${e.message}")
        }
    }

}