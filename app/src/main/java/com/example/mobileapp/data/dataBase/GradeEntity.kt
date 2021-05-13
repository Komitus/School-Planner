package com.example.mobileapp.data.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.time.LocalDateTime


@Entity(tableName = "grades_table")
data class GradeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val value: Int,
    val category:String,
    val date : LocalDateTime,
    val course : Int,
)
