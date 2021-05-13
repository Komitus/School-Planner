package com.example.mobileapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.R
import com.example.mobileapp.MainActivity
import com.example.mobileapp.adapters.GradesAdapter
import com.example.mobileapp.adapters.PlanAdapter
import com.example.mobileapp.data.PlanDay
import java.time.DayOfWeek

class GradesFragment(mainActivity: MainActivity) : Fragment() {

    val mainActivity = mainActivity

    lateinit var gradesAdapter: GradesAdapter
    lateinit var gradesManager : RecyclerView.LayoutManager
    lateinit var gradesRecyclerView: RecyclerView
    var planComponents : ArrayList<PlanDay> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_grades, container, false)

        gradesManager = LinearLayoutManager(container?.context)
        gradesAdapter = GradesAdapter(this)

        gradesRecyclerView = rootView.findViewById<RecyclerView>(R.id.gradeRecyclerView)?.apply {
            setHasFixedSize(true)
            layoutManager = gradesManager
            adapter = gradesAdapter
        }!!

        // Inflate the layout for this fragment
        return rootView


    }



}