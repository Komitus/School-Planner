package com.example.mobileapp.actitvities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileapp.adapters.GradesDetailsAdapter
import com.example.mobileapp.data.Entities.GradeEntity
import com.example.mobileapp.data.dataBase.gradesDetailsViewModel.GradesDBFactory
import com.example.mobileapp.data.dataBase.gradesDetailsViewModel.GradesDetailsViewModel
import com.example.mobileapp.databinding.ActivityGradesDetailsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class GradesDetailsActivity : AppCompatActivity() {

    private lateinit var viewModelDatabase: GradesDetailsViewModel
    private lateinit var binding: ActivityGradesDetailsBinding
    private lateinit var adapter: GradesDetailsAdapter
    private var courseId = 0
    private val ADD_GRADE_CODE = 1
    private val EDIT_GRADE_CODE = 2
    lateinit var trashButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGradesDetailsBinding.inflate(layoutInflater)
        val view = binding.root

        trashButton = binding.deleteGradesButton

        courseId = intent.getIntExtra("courseId", 0)
        binding.gradesDetailsCourseName.text = intent.getStringExtra("courseName")

        Log.println(Log.INFO, "ID", courseId.toString())

        viewModelDatabase = ViewModelProvider(this.viewModelStore,
            GradesDBFactory(this.application, courseId)
        ).get(GradesDetailsViewModel::class.java)

        adapter = GradesDetailsAdapter(this)
        viewModelDatabase.readGradesForCourse.observe(this, Observer { list->
            Log.println(Log.INFO, "Grades", "Observer")
            adapter.setGrades(list)
        })

        binding.gradesDetailsRecycler.adapter = adapter
        binding.gradesDetailsRecycler.layoutManager = LinearLayoutManager(this)

        setContentView(view)
    }
    fun addGrade(view: View){
        val addGradeIntent= Intent(this, AddGradeActivity::class.java)
        addGradeIntent.putExtra("intentMode", "add")
        addGradeIntent.putExtra("courseId", courseId)
        this.startActivityForResult(addGradeIntent, ADD_GRADE_CODE)
    }
    fun editGrade(gradeEntity: GradeEntity) {
        val editGradeIntent= Intent(this, AddGradeActivity::class.java)
        editGradeIntent.putExtra("intentMode", "edit")
        editGradeIntent.putExtra("grade", gradeEntity)
        this.startActivityForResult(editGradeIntent, EDIT_GRADE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data != null){
            val grade = data.getSerializableExtra("grade") as GradeEntity
            if(requestCode == ADD_GRADE_CODE){
                viewModelDatabase.addGrade(grade)
            } else {
                viewModelDatabase.updateGrade(grade.id,grade.value!!, grade.category!!, grade.date!!)
            }
        }
    }

    fun removeSelected(view: View){
        viewModelDatabase.removeSelectedGrades(adapter.getSelectedGradesIds())
    }




}