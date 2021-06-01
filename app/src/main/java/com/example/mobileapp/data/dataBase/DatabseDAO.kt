package com.example.mobileapp.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mobileapp.data.Entities.CourseEntity
import com.example.mobileapp.data.Entities.GradeEntity

@Dao
interface DatabseDAO {

    @Insert
    fun addGrade(grade : GradeEntity)

    @Insert
    fun addCourse(course: CourseEntity)
    //"SELECT value FROM grades_table WHERE course = :courseName ORDER BY date DESC LIMIT 4 "
    @Query("SELECT * FROM grades_table")
    fun readAllGrades() : LiveData<List<GradeEntity>>

    @Query("SELECT * FROM courses_table")
    fun readAllCourses(): LiveData<List<CourseEntity>>


}