package com.example.mobileapp.data.dataBase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobileapp.data.Entities.*
import java.text.FieldPosition
import java.time.DayOfWeek
import java.time.LocalDate

class DatabaseRepo(private val databaseDAO: DatabseDAO) {

    val readAllGrades: LiveData<List<GradeEntity>> = databaseDAO.readAllGrades()
    val readAllCourses: LiveData<List<CourseEntity>> = databaseDAO.readAllCourses()
    val readAllLessons: LiveData<List<LessonEntity>> = databaseDAO.readAllLessons()
    val readAllPluses: LiveData<List<PlusEntity>> = databaseDAO.readAllPluses()
    val readSubstitutions: LiveData<List<SubstitutionEntity>> = databaseDAO.readSubstitution(LocalDate.now().toString())

    suspend fun addGrade(gradeEntity: GradeEntity){
        databaseDAO.addGrade(gradeEntity)
    }

    suspend fun addCourse(courseEntity: CourseEntity){
        databaseDAO.addCourse(courseEntity)
    }

    suspend fun addPlusRow(plusEntity: PlusEntity){
        databaseDAO.addPlusRow(plusEntity)
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

    suspend fun deletePlusRow(courseName: String) {
        databaseDAO.deletePlusRow(courseName)
    }
    suspend fun addPlusWithCheck(courseName: String){
        databaseDAO.addPlusWithCheck(courseName)
    }

    suspend fun deleteOnePlus(courseName: String){
        databaseDAO.deleteOnePlus(courseName)
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

    suspend fun addSubstitution(substitutionEntity: SubstitutionEntity){
        databaseDAO.addSubstitution(substitutionEntity)
    }

}