package com.example.mobileapp.data

import java.io.Serializable
import java.time.DayOfWeek

data class PlanDay(val day: DayOfWeek, val lessons: Array<String>) : Serializable{

    init {
        
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlanDay

        if (day != other.day) return false
        if (!lessons.contentEquals(other.lessons)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = day.hashCode()
        result = 31 * result + lessons.contentHashCode()
        return result
    }

}
