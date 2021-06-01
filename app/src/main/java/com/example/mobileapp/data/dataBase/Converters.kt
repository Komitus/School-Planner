package com.example.mobileapp.data.dataBase

import androidx.room.TypeConverter
import com.example.mobileapp.data.Entities.CourseEntity
import java.time.LocalDate

class Converters {
    @TypeConverter
    fun toDate(dateString: String?): LocalDate? {
        return if (dateString == null) {
            null
        } else {
            LocalDate.parse(dateString)
        }
    }

    @TypeConverter
    fun toDateString(date: LocalDate?): String? {
        return date?.toString()
    }

    @TypeConverter
    fun toPair(course: CourseEntity?) : Pair<Int?, String?>?{
        return Pair(course?.id, course?.name)
    }
}