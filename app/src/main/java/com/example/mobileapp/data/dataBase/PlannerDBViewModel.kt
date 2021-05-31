package com.example.mobileapp.data.dataBase

import android.app.Application
import androidx.lifecycle.*
import com.example.mobileapp.data.dataBase.Grades.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlannerDBViewModel(application: Application) : AndroidViewModel(application) {

    val readAllGrades:  LiveData<List<GradeEntity>>
    val readAllCourses: LiveData<List<CourseEntity>>
    val repo: GradesRepo

    init{
        val gradesDAO = PlannerDatabase.getDatabase(application).gradeDao()
        repo = GradesRepo(gradesDAO)
        readAllGrades = repo.readAllGrades
        readAllCourses = repo.readAllCourses
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


}

@Suppress("UNCHECKED_CAST")
class DBFactory(private val app: Application) : ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return PlannerDBViewModel(app) as T
    }
}