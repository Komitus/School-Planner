package com.example.mobileapp.data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDate


@Entity(tableName = "grades_table")
data class GradeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val value: Int? = null,
    val category:String? = null,
    val date : LocalDate? = null,
    val course : Int? = null
) : Serializable
