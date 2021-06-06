package com.example.mobileapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.MainActivity
import com.example.mobileapp.actitvities.AddCourseActivity
import com.example.mobileapp.data.Entities.CourseEntity
import com.example.mobileapp.data.ScheduleItem
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
            val intent = Intent(context, AddCourseActivity::class.java)
            intent.putExtra("courseName", courses[position].name)
            intent.putExtra("teacher", courses[position].teacher)
            intent.putExtra("howMany", courses[position].howMany)


            val allLessons = context.viewModelDatabase.readAllLessons
            val allScheduleItems = arrayListOf<ScheduleItem>()
            val myCourseScheduleItems = arrayListOf<ScheduleItem>()
            for (lesson in allLessons.value!!) {
                val scheduleItem = ScheduleItem(context.converters.intToDay(lesson.dayOfWeek).toString(), lesson.lessonNumber.toString())
                if (lesson.courseName == courses[position].name) {
                    myCourseScheduleItems.add(scheduleItem)
                }
                allScheduleItems.add(scheduleItem)
            }
            intent.putExtra("allLessons", allScheduleItems)
            intent.putExtra("myLessons", myCourseScheduleItems)
            intent.putExtra("mode", 1)
            val allCourses = context.viewModelDatabase.readAllCourses
            val courseNames : ArrayList<String> = arrayListOf()
            for (course in allCourses.value!!) {
                courseNames.add(course.name)
            }
            intent.putExtra("courseNames", courseNames)
            context.startActivityForResult(intent, 123)
        }
        holder.itemView.setOnLongClickListener {
            context.viewModelDatabase.deleteAllCourseInfo(courses[position].name, courses[position].id)
            notifyDataSetChanged()
            true
        }

    }


    fun setCourseList(passedList: List<CourseEntity>) {
        courses.clear()
        for (course in passedList) {
            courses.add(course)
        }
        notifyDataSetChanged()
    }


    private fun removeAtPosition(position: Int) {
        courses.removeAt(position)
    }




}