package com.example.mobileapp.data.dataBase

import androidx.room.TypeConverter
import com.example.mobileapp.data.dataBase.Grades.CourseEntity
import java.time.LocalDateTime

class Converters {
    @TypeConverter
    fun toDate(dateString: String?): LocalDateTime? {
        return if (dateString == null) {
            null
        } else {
            LocalDateTime.parse(dateString)
        }
    }

    @TypeConverter
    fun toDateString(date: LocalDateTime?): String? {
        return date?.toString()
    }

    @TypeConverter
    fun toPair(course: CourseEntity?) : Pair<Int?, String?>?{
        return Pair(course?.id, course?.name)
    }
}