package edu.bu.nutritiontracker.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.bu.nutritiontracker.data.db.DailyFoods
import edu.bu.nutritiontracker.data.db.DailyFoodsDao
import edu.bu.nutritiontracker.data.db.DbInitializer
import edu.bu.nutritiontracker.data.db.Food
import edu.bu.nutritiontracker.data.db.FoodDao
import edu.bu.nutritiontracker.data.db.NutritionDatabase
import edu.bu.nutritiontracker.data.repository.DailyFoodsRepository
import edu.bu.nutritiontracker.data.repository.FoodRepository
import java.time.LocalDate
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalDatabase(
        @ApplicationContext context: Context,
        foodProvider: Provider<FoodDao>,
        dailyFoodsProvider: Provider<DailyFoodsDao>
    ): NutritionDatabase{
        return Room.databaseBuilder(
            context,
            NutritionDatabase::class.java,
            "nutrition_db"
        ).createFromAsset("nutrition_db").build()
    }

    @Provides
    @Singleton
    fun provideFoodDao(db: NutritionDatabase): FoodDao = db.foodDao()

    @Provides
    @Singleton
    fun provideDailyFoodsDao(db: NutritionDatabase): DailyFoodsDao = db.dailyFoodsDao()

    @Provides
    @Singleton
    fun providesFoodRepository(foodDao: FoodDao): FoodRepository = FoodRepository(foodDao = foodDao)

    @Provides
    @Singleton
    fun provideDailyFoodsRepository(dailyFoodsDao: DailyFoodsDao) = DailyFoodsRepository(dailyFoodsDao = dailyFoodsDao)

    @Provides
    @Singleton
    fun provideDbInitializer(
        context: Context,
        foodProvider: Provider<FoodDao>,
        dailyFoodsProvider: Provider<DailyFoodsDao>
    ): DbInitializer {
        return DbInitializer(context, foodProvider, dailyFoodsProvider)
    }
}
