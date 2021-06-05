package com.example.mobileapp.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.MainActivity
import com.example.mobileapp.actitvities.EditCourseActivity
import com.example.mobileapp.data.Entities.CourseEntity
import com.example.mobileapp.data.Entities.GradeEntity
import com.example.mobileapp.databinding.CourseItemBinding

class CourseAdapter(private val context: MainActivity) : RecyclerView.Adapter<CourseAdapter.ViewHolder>(){

    var courses : ArrayList<CourseEntity> = ArrayList()

    inner class ViewHolder(val binding: CourseItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseAdapter.ViewHolder {
        return ViewHolder(CourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.courseAbbreviation.text = courses[position].abbreviation
        holder.binding.courseName.text = courses[position].name

        holder.itemView.setOnClickListener {
            val intent = Intent(context, EditCourseActivity::class.java)
            intent.putExtra("courseName", courses[position].name)
            intent.putExtra("teacher", courses[position].teacher)
            intent.putExtra("howMany", courses[position].howMany)
            context.startActivityForResult(intent, 123)
        }

    }


    fun setCourseList(passedList: List<CourseEntity>) {
        courses.clear()
        for (course in passedList) {
            courses.add(course)
        }
        notifyDataSetChanged()
    }





}