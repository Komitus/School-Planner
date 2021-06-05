package com.example.mobileapp.data.dataBase.gradesDetailsViewModel

import androidx.lifecycle.LiveData
import com.example.mobileapp.data.Entities.CourseEntity
import com.example.mobileapp.data.Entities.GradeEntity
import com.example.mobileapp.data.Entities.LessonEntity
import com.example.mobileapp.data.dataBase.DatabseDAO
import java.time.LocalDate

class GradesDetailsRepo(private val databaseDAO: DatabseDAO, private val courseId: Int) {

    val readAllGrades: LiveData<List<GradeEntity>> = databaseDAO.readGradesForCourse(courseId)

    suspend fun addGrade(gradeEntity: GradeEntity){
        databaseDAO.addGrade(gradeEntity)
    }

    suspend fun updateGrade(gradeId: Int, value: Int, category: String, date: LocalDate) {
        databaseDAO.updateGrade(gradeId, value, category, date)
    }

    suspend fun removeSelectedGrades(selectedGrades: List<Int>) {
        databaseDAO.removeAllSelectedGrades(selectedGrades)
    }
}