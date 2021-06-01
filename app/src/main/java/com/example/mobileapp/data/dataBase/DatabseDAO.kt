package com.example.mobileapp.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mobileapp.data.Entities.CourseEntity
import com.example.mobileapp.data.Entities.GradeEntity
import com.example.mobileapp.data.Entities.LessonEntity
import com.example.mobileapp.data.Entities.PlanDay
import java.time.DayOfWeek

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

    @Query("SELECT * FROM lessons_table WHERE dayOfWeek = :day")
    fun getLessonsByDay(day: Int) : LiveData<List<LessonEntity>>

    @Query("SELECT * FROM lessons_table")
    fun readAllLessons() : LiveData<List<LessonEntity>>

    @Delete
    fun deletePrev(lesson: LessonEntity)

    @Query("SELECT COUNT(*) FROM lessons_table")
    fun isFirstTime() : Int

    @Insert
    fun addNew(lesson: LessonEntity)

}