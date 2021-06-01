package com.example.mobileapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.R
import com.example.mobileapp.adapters.PlanAdapter
import com.example.mobileapp.data.Entities.PlanDay
import java.time.DayOfWeek

class PlanFragment : Fragment() {

    lateinit var planAdapter: PlanAdapter
    lateinit var planManager : RecyclerView.LayoutManager
    lateinit var planRecyclerView: RecyclerView
    var planComponents : ArrayList<PlanDay> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_plan, container, false)
        var dummyLessons = arrayOf<String>(
                "test1",
                "test2",
                "test3",
                "test4",
                "test5",
                "test6",
                "test7",
                "test8"
        )
        var day = PlanDay(DayOfWeek.MONDAY, dummyLessons)
        planComponents.add(day)

        planManager = LinearLayoutManager(container?.context)
        planAdapter = PlanAdapter(planComponents)
        planRecyclerView = rootView.findViewById<RecyclerView>(R.id.planRecyclerView)?.apply {
            setHasFixedSize(true)
            layoutManager = planManager
            adapter = planAdapter
        }!!

        // Inflate the layout for this fragment
        return rootView


    }




}