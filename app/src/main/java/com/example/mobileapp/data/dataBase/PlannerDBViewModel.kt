package com.example.mobileapp.data.dataBase

import android.app.Application
import androidx.lifecycle.*
import com.example.mobileapp.data.Entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlannerDBViewModel(application: Application) : AndroidViewModel(application) {

    val readAllGrades:  LiveData<List<GradeEntity>>
    val readAllCourses: LiveData<List<CourseEntity>>
    val readAllLessons: LiveData<List<LessonEntity>>
    val readAllPluses: LiveData<List<PlusEntity>>
    private val repo: DatabaseRepo

    init{
        val databaseDAO = PlannerDatabase.getDatabase(application).databaseDAO()
        repo = DatabaseRepo(databaseDAO)
        readAllGrades = repo.readAllGrades
        readAllCourses = repo.readAllCourses
        readAllLessons = repo.readAllLessons
        readAllPluses = repo.readAllPluses
    }

    fun addCourse(course: CourseEntity){
        viewModelScope.launch(Dispatchers.IO){
            repo.addCourse(course)
        }
    }

    fun addGrade(grade: GradeEntity){
        viewModelScope.launch(Dispatchers.IO){
            repo.addGrade(grade)
        }
    }

    fun addPlusRow(plusEntity: PlusEntity){
        viewModelScope.launch(Dispatchers.IO){
            repo.addPlusRow(plusEntity)
        }
    }


    fun addLesson(lesson: LessonEntity) {
        viewModelScope.launch(Dispatchers.IO){
            repo.addLesson(lesson)
        }
    }


    fun deleteOld(courseName : String) {
        viewModelScope.launch(Dispatchers.IO){
            repo.deleteOld(courseName)
        }
    }

    fun deleteOldLessons(day: Int, lesson: Int) {
        viewModelScope.launch(Dispatchers.IO){
            repo.deleteOldLessons(day, lesson)
        }
    }

    fun deletePlusRow(courseName: String){
        viewModelScope.launch(Dispatchers.IO){
            repo.deletePlusRow(courseName)
        }
    }

    fun addPlusWithCheck(courseName: String){
        viewModelScope.launch(Dispatchers.IO){
            repo.addPlusWithCheck(courseName)
        }
    }
    fun deleteOnePlus(courseName: String){
        viewModelScope.launch(Dispatchers.IO){
            repo.deleteOnePlus(courseName)
        }
    }

    fun deleteAllCourseInfo(courseName: String, courseId: Int) {
        viewModelScope.launch(Dispatchers.IO){
            repo.deleteCourseInfo(courseName, courseId)
        }
    }

    fun updateLesson(course: String, day: Int, lessonNumber: Int) {
        viewModelScope.launch(Dispatchers.IO){
            repo.updateLesson(course, day, lessonNumber)
        }
    }

    fun updateCourse(name: String, pluses: Int, teacher: String) {
        viewModelScope.launch(Dispatchers.IO){
            repo.updateCourse(name, pluses, teacher)
        }
    }

    fun deleteLesson(lessonNumber: Int, day: Int) {
        viewModelScope.launch(Dispatchers.IO){
            repo.deleteLesson(day, lessonNumber)
        }
    }



}

@Suppress("UNCHECKED_CAST")
class DBFactory(private val app: Application) : ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return PlannerDBViewModel(app) as T
    }
}