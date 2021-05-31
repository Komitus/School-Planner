package com.example.mobileapp.data.dataBase.Grades

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GradesRepo(private val gradesDAO: GradesDAO) {

    val readAllGrades: LiveData<List<GradeEntity>> = gradesDAO.readAllGrades()
    val readAllCourses: LiveData<List<CourseEntity>> = gradesDAO.readAllCourses()

    suspend fun addGrade(gradeEntity: GradeEntity){
        gradesDAO.addGrade(gradeEntity)
    }

    suspend fun addCourse(courseEntity: CourseEntity){
        gradesDAO.addCourse(courseEntity)
    }


}