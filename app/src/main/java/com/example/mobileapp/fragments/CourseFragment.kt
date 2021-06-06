package com.example.mobileapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.MainActivity
import com.example.mobileapp.R
import com.example.mobileapp.actitvities.AddCourseActivity
import com.example.mobileapp.adapters.CourseAdapter
import com.example.mobileapp.data.Entities.CourseEntity
import com.example.mobileapp.data.Entities.LessonEntity
import com.example.mobileapp.data.ScheduleItem
import com.example.mobileapp.data.dataBase.Converters
import com.example.mobileapp.toasts.ToastMaker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import www.sanju.motiontoast.MotionToast

class CourseFragment : Fragment() {

    lateinit var context : MainActivity
    private lateinit var courseAdapter: CourseAdapter
    private lateinit var courseManager : RecyclerView.LayoutManager
    private lateinit var courseRecyclerView: RecyclerView
    private lateinit var addButton : FloatingActionButton
    private val converters = Converters()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as MainActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_course, container, false)
        courseAdapter = CourseAdapter(context)
        addButton = rootView.findViewById<FloatingActionButton>(R.id.addCourseButton)
        addButton.setOnClickListener {
            val intent = Intent(context, AddCourseActivity::class.java)
            val allCourses = context.viewModelDatabase.readAllCourses
            val courseNames : ArrayList<String> = arrayListOf()
            for (course in allCourses.value!!) {
                courseNames.add(course.name)
            }
            intent.putExtra("courseNames", courseNames)
            val allLessons = context.viewModelDatabase.readAllLessons
            val allScheduleItems : ArrayList<ScheduleItem> = arrayListOf()
            for (lesson in allLessons.value!!) {
                val scheduleItem = ScheduleItem(context.converters.intToDay(lesson.dayOfWeek).toString(), lesson.lessonNumber.toString())
                allScheduleItems.add(scheduleItem)
            }
            intent.putExtra("allLessons", allScheduleItems)
            startActivityForResult(intent, 1)
        }

        context.viewModelDatabase.readAllCourses.observe(this.viewLifecycleOwner, Observer {
            courseAdapter.setCourseList(it)
        })

        courseManager = LinearLayoutManager(container?.context)
        courseRecyclerView = rootView.findViewById<RecyclerView>(R.id.courseRecyclerView)?.apply {
            setHasFixedSize(true)
            layoutManager = courseManager
            adapter = courseAdapter
        }!!

        return rootView
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && data != null) {
                val name = data.getStringExtra("courseName")
                val abb = name?.substring(0, 2)
                val teacher = data.getStringExtra("teacher")
                val pluses = data.getIntExtra("pluses", 0)
                if (teacher != null) {
                    abb?.let { CourseEntity(0, name, it, pluses, teacher) }?.let { context.viewModelDatabase.addCourse(it) }
                }
                ToastMaker.makeSuccessToast(context, "Course is added")

                val courseSchedules = data.getSerializableExtra("schedule")
                Log.d("am2021", courseSchedules.toString())

                for (schedule in courseSchedules as ArrayList<ScheduleItem>) {
                    if (name != null) {
                        context.viewModelDatabase.updateLesson(name, converters.StringDayToInt(schedule.day), schedule.lesson.toInt())
                    }
                }

                val courseSchedulesToAdd = data.getSerializableExtra("scheduleToAdd")
                for (schedule in courseSchedulesToAdd as ArrayList<ScheduleItem>) {
                    if (name != null) {
                        context.viewModelDatabase.addLesson(LessonEntity(converters.StringDayToInt(schedule.day), name, schedule.lesson.toInt()))
                    }
                }

        }

    }
}