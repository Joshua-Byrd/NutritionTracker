package edu.bu.nutritiontracker.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class DateTimeConverter {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    @TypeConverter
    fun toDate(timeStamp: String?): LocalDateTime? {
        return timeStamp?.let { LocalDateTime.parse(it)}
    }

    @TypeConverter
    fun toTimeStamp(date: Date?): String? {
        return date?.toString()
    }
}