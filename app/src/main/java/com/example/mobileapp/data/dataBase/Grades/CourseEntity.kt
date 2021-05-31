package com.example.mobileapp.data.dataBase.Grades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses_table")
data class CourseEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)