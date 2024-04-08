package edu.bu.nutritiontracker.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.bu.nutritiontracker.data.db.DailyFoodEntryWithFood
import edu.bu.nutritiontracker.data.db.DailyFoods
import edu.bu.nutritiontracker.data.db.Food
import edu.bu.nutritiontracker.data.db.NutritionDatabase
import edu.bu.nutritiontracker.data.repository.DailyFoodsRepository
import edu.bu.nutritiontracker.data.repository.FoodRepository
import edu.bu.nutritiontracker.util.getTestFoodList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FoodViewModel @Inject constructor(
    private val foodRepository: FoodRepository,
    ): ViewModel() {



    private val _foodSearchResultList = MutableStateFlow<List<Food>>(emptyList())
    private val _food = MutableStateFlow<Food?>(null)




    //expose immutables

    val foodSearchResultList = _foodSearchResultList.asStateFlow()
    val food = _food.asStateFlow()




    init {

    }




}