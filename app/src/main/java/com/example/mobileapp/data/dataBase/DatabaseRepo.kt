package com.example.mobileapp.data.dataBase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobileapp.data.Entities.CourseEntity
import com.example.mobileapp.data.Entities.GradeEntity
import com.example.mobileapp.data.Entities.LessonEntity
import com.example.mobileapp.data.Entities.PlanDay
import java.text.FieldPosition
import java.time.DayOfWeek

class DatabaseRepo(private val databseDAO: DatabseDAO) {

    val readAllGrades: LiveData<List<GradeEntity>> = databseDAO.readAllGrades()
    val readAllCourses: LiveData<List<CourseEntity>> = databseDAO.readAllCourses()
    val readAllLessons: LiveData<List<LessonEntity>> = databseDAO.readAllLessons()

    suspend fun addGrade(gradeEntity: GradeEntity){
        databseDAO.addGrade(gradeEntity)
    }

    suspend fun addCourse(courseEntity: CourseEntity){
        databseDAO.addCourse(courseEntity)
    }

    suspend fun addLesson(lessonEntity: LessonEntity) {
        databseDAO.addNew(lessonEntity)
    }



}