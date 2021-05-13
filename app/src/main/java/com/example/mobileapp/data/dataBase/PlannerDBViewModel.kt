package com.example.mobileapp.data.dataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlannerDBViewModel(application: Application) : AndroidViewModel(application) {

    val gradesDAO : GradesDAO

    init{
        gradesDAO = PlannerDatabase.getDatabase(application).gradeDao()
    }
    fun readLastGrades() : LiveData<List<Int>>?{

        var toRet : LiveData<List<Int>>? = null
        viewModelScope.launch(Dispatchers.IO){
            toRet = gradesDAO.readLastGradesForCourse()
        }
        return toRet
    }
    fun addGrade(grade : GradeEntity){

        viewModelScope.launch(Dispatchers.IO){
            gradesDAO.addGrade(grade)
        }
    }
}