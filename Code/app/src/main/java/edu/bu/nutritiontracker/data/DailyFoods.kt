package edu.bu.nutritiontracker.data

data class DailyFoods(
    val foodEntryId: Int,
    val foodId: Int, //foreign key to food?
    val date: String //date or text for SQLite?
){}