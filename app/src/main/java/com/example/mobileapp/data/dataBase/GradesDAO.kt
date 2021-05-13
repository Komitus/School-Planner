package com.example.mobileapp.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GradesDAO {

    @Insert
    suspend fun addGrade(grade : GradeEntity)
    //"SELECT value FROM grades_table WHERE course = :courseName ORDER BY date DESC LIMIT 4 "
    @Query("SELECT value FROM grades_table")
    fun readLastGradesForCourse() : LiveData<List<Int>>
}