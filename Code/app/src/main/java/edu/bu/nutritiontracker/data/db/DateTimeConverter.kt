package edu.bu.nutritiontracker.data.db

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class DateTimeConverter {

        private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

        @TypeConverter
        fun toDate(timeStamp: String): LocalDate{
            return LocalDate.parse(timeStamp, formatter)
        }

        @TypeConverter
        fun toString (date: LocalDate): String {
            return date.toString()
        }
}