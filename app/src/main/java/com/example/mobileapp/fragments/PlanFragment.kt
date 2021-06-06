package com.example.mobileapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.mobileapp.actitvities.AddSubActivity
import com.example.mobileapp.adapters.PlanAdapter
import com.example.mobileapp.data.Entities.LessonEntity
import com.example.mobileapp.data.Entities.PlanDay
import com.example.mobileapp.data.Entities.SubstitutionEntity
import com.example.mobileapp.data.dataBase.Converters
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.DayOfWeek
import java.time.LocalDate

class PlanFragment : Fragment() {

    lateinit var planAdapter: PlanAdapter
    lateinit var planManager : RecyclerView.LayoutManager
    lateinit var planRecyclerView: RecyclerView
    private lateinit var context: MainActivity
    private val ADD_SUB_CODE = 1
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_plan, container, false)
        val button = rootView.findViewById<FloatingActionButton>(R.id.addSub)
        button.setOnClickListener { addSub()}
        planAdapter = PlanAdapter(context)

        context.viewModelDatabase.readAllLessons.observe(context, Observer {
            planAdapter.setMyDaysList(it)
        })

        context.viewModelDatabase.readSubstitutions.observe(this.viewLifecycleOwner, Observer {
            planAdapter.setSubstitutions(it)
        })

        planManager = LinearLayoutManager(container?.context)

        planRecyclerView = rootView.findViewById<RecyclerView>(R.id.planRecyclerView)?.apply {
            setHasFixedSize(true)
            layoutManager = planManager
            adapter = planAdapter
        }!!

        return rootView
    }

    private fun addSub(){
        val allCourses = context.viewModelDatabase.readAllCourses.value
        if(allCourses!= null && allCourses.isNotEmpty()){
            val intent = Intent(context, AddSubActivity::class.java)
            val list = arrayListOf<String>()
            for(item in allCourses){
                list.add(item.name)
            }
            intent.putExtra("listOfCourses", list)
            startActivityForResult(intent, ADD_SUB_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADD_SUB_CODE && data != null){
            val subst = data.getSerializableExtra("subst") as SubstitutionEntity
            context.viewModelDatabase.addSubstitution(subst)
        }
    }


}