package com.example.mobileapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.R

import com.example.mobileapp.adapters.GradesAdapter
import com.example.mobileapp.data.dataBase.Grades.GradeEntity
import com.example.mobileapp.data.dataBase.PlannerDBViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDateTime
import androidx.lifecycle.Observer
import com.example.mobileapp.MainActivity

class GradesFragment(val ref: PlannerDBViewModel) : Fragment() {

    private lateinit var gradesAdapter: GradesAdapter
    private lateinit var gradesManager : RecyclerView.LayoutManager
    private lateinit var gradesRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_grades, container, false)

        rootView.findViewById<FloatingActionButton>(R.id.addGradeButton).setOnClickListener {
            addGrade()
        }

        gradesAdapter = GradesAdapter()

        ref.readAllCourses.observe(this.viewLifecycleOwner, Observer {
            gradesAdapter.setCourses(it)
        })

        ref.readAllGrades.observe(this.viewLifecycleOwner, Observer {
            gradesAdapter.setList(it)
        })

        gradesManager = LinearLayoutManager(container?.context)
        gradesRecyclerView = rootView.findViewById<RecyclerView>(R.id.gradeRecyclerView)?.apply {
            setHasFixedSize(true)
            layoutManager = gradesManager
            adapter = gradesAdapter
        }!!

        // Inflate the layout for this fragment
        return rootView
    }

    private fun addGrade() {
        val grade1 = GradeEntity(0, 1, "sprawdzian", LocalDateTime.now(), 1)
        val grade2 = GradeEntity(0, 5, "sprawdzian", LocalDateTime.now(), 1)
        val grade3 = GradeEntity(0, 6, "kartówka", LocalDateTime.now(), 2)
        val grade4 = GradeEntity(0, 2, "sprawdzian", LocalDateTime.now(), 3)

        ref.addGrade(grade1)
        ref.addGrade(grade2)
        ref.addGrade(grade3)
        ref.addGrade(grade4)
    }


}