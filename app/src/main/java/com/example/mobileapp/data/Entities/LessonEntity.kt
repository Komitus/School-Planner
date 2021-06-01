package com.example.mobileapp.data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.DayOfWeek

@Entity(tableName = "lessons_table", primaryKeys = ["dayOfWeek", "lessonNumber"])
data class LessonEntity(val dayOfWeek: Int, val courseName: String = "----", val lessonNumber: Int)