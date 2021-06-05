package com.example.mobileapp.data.dataBase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobileapp.data.Entities.CourseEntity
import com.example.mobileapp.data.Entities.GradeEntity
import com.example.mobileapp.data.Entities.LessonEntity
import com.example.mobileapp.data.Entities.PlanDay
import java.text.FieldPosition
import java.time.DayOfWeek

class DatabaseRepo(private val databaseDAO: DatabseDAO) {

    val readAllGrades: LiveData<List<GradeEntity>> = databaseDAO.readAllGrades()
    val readAllCourses: LiveData<List<CourseEntity>> = databaseDAO.readAllCourses()
    val readAllLessons: LiveData<List<LessonEntity>> = databaseDAO.readAllLessons()

    suspend fun addGrade(gradeEntity: GradeEntity){
        databaseDAO.addGrade(gradeEntity)
    }

    suspend fun addCourse(courseEntity: CourseEntity){
        databaseDAO.addCourse(courseEntity)
    }

    suspend fun addLesson(lessonEntity: LessonEntity) {
        databaseDAO.addNew(lessonEntity)
    }

    suspend fun deleteOld(courseName : String) {
        databaseDAO.deleteOld(courseName)
    }

    suspend fun deleteOldLessons(day: Int, lesson: Int) {
        databaseDAO.deleteOldLessons(day, lesson)
    }

    suspend fun deleteCourseInfo(courseName: String, courseId: Int) {
        databaseDAO.deleteAllCourseInfo(courseName, courseId)
    }

    fun updateLesson(course: String, day: Int, lessonNumber: Int) {
        databaseDAO.updateLesson(course, day, lessonNumber)
    }
    fun updateCourse(name: String, pluses: Int, teacher: String) {
        databaseDAO.updateCourse(name, pluses, teacher)
    }

    fun deleteLesson(day: Int, lessonNumber: Int) {
        databaseDAO.deleteLesson(lessonNumber, day)
    }




}