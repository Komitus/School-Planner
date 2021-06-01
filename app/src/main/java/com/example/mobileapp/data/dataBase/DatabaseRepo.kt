package com.example.mobileapp.data.dataBase

import androidx.lifecycle.LiveData
import com.example.mobileapp.data.Entities.CourseEntity
import com.example.mobileapp.data.Entities.GradeEntity

class DatabaseRepo(private val databseDAO: DatabseDAO) {

    val readAllGrades: LiveData<List<GradeEntity>> = databseDAO.readAllGrades()
    val readAllCourses: LiveData<List<CourseEntity>> = databseDAO.readAllCourses()

    suspend fun addGrade(gradeEntity: GradeEntity){
        databseDAO.addGrade(gradeEntity)
    }

    suspend fun addCourse(courseEntity: CourseEntity){
        databseDAO.addCourse(courseEntity)
    }


}