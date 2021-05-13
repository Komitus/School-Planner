package com.example.mobileapp.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.R
import com.example.mobileapp.fragments.GradesFragment


class GradesAdapter(private val gradeFragment: GradesFragment) : RecyclerView.Adapter<GradesAdapter.ViewHolder>(){

    val courses = mutableListOf<String>("Polski", "Angielski", "Matma")

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val courseAbbr : TextView
        val courseName : TextView
        val lastGradesText : TextView
        var lastGrades : LiveData<List<Int>>? = null
        init {
            courseAbbr = view.findViewById(R.id.courseAbbreviation)
            courseName = view.findViewById(R.id.courseGrades)
            lastGradesText = view.findViewById(R.id.lastGrades)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grade_item, parent, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.lastGrades = gradeFragment.mainActivity.viewModelDatabase.readLastGrades()
        viewHolder.courseName.text = courses[position]
        viewHolder.courseAbbr.text = "JP"
        viewHolder.lastGradesText.text = viewHolder.lastGrades?.value?.lastIndex.toString()
    }

    override fun getItemCount(): Int {
        return courses.size
    }

}