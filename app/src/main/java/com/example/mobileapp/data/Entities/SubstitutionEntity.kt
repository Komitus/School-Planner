package com.example.mobileapp.data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDate

@Entity(tableName = "substitutions_table")
data class SubstitutionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dayOfWeek: Int,
    val courseName: String,
    val lessonNumber: Int,
    val date : LocalDate
    ) : Serializable