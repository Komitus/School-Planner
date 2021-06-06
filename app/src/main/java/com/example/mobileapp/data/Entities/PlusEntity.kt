package com.example.mobileapp.data.Entities

import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "pluses_table", primaryKeys = ["courseName"])
data class PlusEntity(val courseName: String, val actual: Int, val used: Int) : Serializable