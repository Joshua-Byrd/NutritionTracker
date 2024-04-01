package edu.bu.nutritiontracker.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FoodViewModel: ViewModel() {

    private val _food = MutableStateFlow<Food?>(null)

    val food = _food.asStateFlow()

    init {

    }
}