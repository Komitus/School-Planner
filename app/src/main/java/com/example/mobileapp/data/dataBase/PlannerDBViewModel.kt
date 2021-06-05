package com.example.mobileapp.data.dataBase

import android.app.Application
import androidx.lifecycle.*
import com.example.mobileapp.data.Entities.CourseEntity
import com.example.mobileapp.data.Entities.GradeEntity
import com.example.mobileapp.data.Entities.LessonEntity
import com.example.mobileapp.data.Entities.PlanDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlannerDBViewModel(application: Application) : AndroidViewModel(application) {

    val readAllGrades:  LiveData<List<GradeEntity>>
    val readAllCourses: LiveData<List<CourseEntity>>
    val readAllLessons : LiveData<List<LessonEntity>>
    private val repo: DatabaseRepo

    init{
        val databaseDAO = PlannerDatabase.getDatabase(application).databaseDAO()
        repo = DatabaseRepo(databaseDAO)
        readAllGrades = repo.readAllGrades
        readAllCourses = repo.readAllCourses
        readAllLessons = repo.readAllLessons

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


}

@Suppress("UNCHECKED_CAST")
class DBFactory(private val app: Application) : ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return PlannerDBViewModel(app) as T
    }
}