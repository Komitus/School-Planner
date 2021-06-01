package com.example.mobileapp.data.Entities

import androidx.room.Entity
import java.io.Serializable
import java.time.DayOfWeek


data class PlanDay(val day: DayOfWeek, val lessons: Array<LessonEntity?>)

