package com.example.mobileapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.MainActivity
import com.example.mobileapp.R
import com.example.mobileapp.adapters.GradesAdapter

class GradesFragment : Fragment() {

    private lateinit var gradesAdapter: GradesAdapter
    private lateinit var gradesRecyclerView: RecyclerView
    private lateinit var context: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_grades, container, false)

        gradesAdapter = GradesAdapter(context)

        context.viewModelDatabase.readAllCourses.observe(this.viewLifecycleOwner, Observer {
            gradesAdapter.setCourses(it)
        })

        context.viewModelDatabase.readAllGrades.observe(this.viewLifecycleOwner, Observer {
            gradesAdapter.setList(it)
        })

        gradesRecyclerView = rootView.findViewById<RecyclerView>(R.id.gradeRecyclerView)?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(container?.context)
            adapter = gradesAdapter
        }!!
        return rootView
    }

}