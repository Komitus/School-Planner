package com.example.mobileapp.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mobileapp.data.Entities.CourseEntity
import com.example.mobileapp.data.Entities.GradeEntity
import com.example.mobileapp.data.Entities.LessonEntity
import java.nio.file.Files.delete
import java.time.LocalDate


@Dao
interface DatabseDAO {

    @Insert
    fun addGrade(grade : GradeEntity)

    @Insert
    fun addCourse(course: CourseEntity)
    //"SELECT value FROM grades_table WHERE course = :courseName ORDER BY date DESC LIMIT 4 "
    @Query("SELECT * FROM grades_table")
    fun readAllGrades() : LiveData<List<GradeEntity>>

    @Query("SELECT * FROM grades_table WHERE course = :courseId")
    fun readGradesForCourse(courseId: Int) : LiveData<List<GradeEntity>>

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

    @Query("DELETE FROM courses_table WHERE name = :name")
    fun deleteOld(name: String)

    @Query("DELETE FROM lessons_table WHERE dayOfWeek = :day AND lessonNumber = :lesson")
    fun deleteOldLessons(day : Int, lesson : Int)

    @Query("UPDATE grades_table SET value = :value, category = :category, date = :date WHERE id = :gradeId")
    fun updateGrade(gradeId: Int, value: Int, category: String, date: LocalDate)

    @Query("DELETE FROM grades_table WHERE id = :gradeId")
    fun removeSelectedGrade(gradeId: Int)

    @Transaction
    fun removeAllSelectedGrades(selectedGradesIds: List<Int>) {
        for(idx in selectedGradesIds){
            removeSelectedGrade(idx)
        }
    }

}