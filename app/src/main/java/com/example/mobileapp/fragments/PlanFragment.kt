package com.example.mobileapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.MainActivity
import com.example.mobileapp.R
import com.example.mobileapp.adapters.PlanAdapter
import com.example.mobileapp.data.Entities.LessonEntity
import com.example.mobileapp.data.Entities.PlanDay
import com.example.mobileapp.data.dataBase.Converters
import java.time.DayOfWeek

class PlanFragment : Fragment() {

    lateinit var planAdapter: PlanAdapter
    lateinit var planManager : RecyclerView.LayoutManager
    lateinit var planRecyclerView: RecyclerView
    private lateinit var context: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_plan, container, false)

        planAdapter = PlanAdapter(context)
        context.viewModelDatabase.readAllLessons.observe(context, Observer {
            planAdapter.setMyDaysList(it)
        })

        planManager = LinearLayoutManager(container?.context)

        planRecyclerView = rootView.findViewById<RecyclerView>(R.id.planRecyclerView)?.apply {
            setHasFixedSize(true)
            layoutManager = planManager
            adapter = planAdapter
        }!!

        return rootView
    }


}