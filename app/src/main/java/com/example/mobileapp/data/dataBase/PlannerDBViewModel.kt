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
        val gradesDAO = PlannerDatabase.getDatabase(application).gradeDao()
        repo = DatabaseRepo(gradesDAO)
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




}

@Suppress("UNCHECKED_CAST")
class DBFactory(private val app: Application) : ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return PlannerDBViewModel(app) as T
    }
}