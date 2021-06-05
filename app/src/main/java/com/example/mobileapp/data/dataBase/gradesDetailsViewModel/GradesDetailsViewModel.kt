package com.example.mobileapp.data.dataBase.gradesDetailsViewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.mobileapp.data.Entities.GradeEntity
import com.example.mobileapp.data.dataBase.PlannerDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class GradesDetailsViewModel(application: Application, courseId: Int) : AndroidViewModel(application)  {
    val readGradesForCourse: LiveData<List<GradeEntity>>
    private val repo: GradesDetailsRepo

    init{
        val databaseDAO = PlannerDatabase.getDatabase(application).databaseDAO()
        repo = GradesDetailsRepo(databaseDAO, courseId)
        readGradesForCourse = repo.readAllGrades
    }

    fun addGrade(grade: GradeEntity){
        viewModelScope.launch(Dispatchers.IO){
            repo.addGrade(grade)
        }
    }

    fun updateGrade(gradeId: Int, value: Int, category: String, date: LocalDate){
        viewModelScope.launch(Dispatchers.IO){
            repo.updateGrade(gradeId, value, category, date)
        }
    }

    fun removeSelectedGrades(selectedGradesIds: List<Int>) {
        viewModelScope.launch(Dispatchers.IO){
            repo.removeSelectedGrades(selectedGradesIds)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class GradesDBFactory(private val app: Application, private val courseId: Int) : ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return GradesDetailsViewModel(app, courseId) as T
    }
}