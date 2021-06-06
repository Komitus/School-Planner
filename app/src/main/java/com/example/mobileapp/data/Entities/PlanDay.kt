package com.example.mobileapp.data.Entities

import androidx.room.Entity
import java.io.Serializable
import java.time.DayOfWeek
import java.time.LocalDate


data class PlanDay(val date: LocalDate, val lessons: Array<LessonEntity?>)

