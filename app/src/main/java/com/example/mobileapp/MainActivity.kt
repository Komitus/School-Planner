package com.example.mobileapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mobileapp.data.Constants
import com.example.mobileapp.data.Entities.CourseEntity
import com.example.mobileapp.data.Entities.GradeEntity
import com.example.mobileapp.data.Entities.LessonEntity
import com.example.mobileapp.data.Entities.PlusEntity
import com.example.mobileapp.data.ScheduleItem
import com.example.mobileapp.data.dataBase.Converters
import com.example.mobileapp.data.dataBase.DBFactory
import com.example.mobileapp.data.dataBase.PlannerDBViewModel
import com.example.mobileapp.fragments.CourseFragment
import com.example.mobileapp.fragments.GradesFragment
import com.example.mobileapp.fragments.PlanFragment
import com.example.mobileapp.fragments.PlusFragment
import com.example.mobileapp.toasts.ToastMaker
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    lateinit var viewModelDatabase: PlannerDBViewModel
    val converters = Converters()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelDatabase = ViewModelProvider(this.viewModelStore,
            DBFactory(this.application)).get(PlannerDBViewModel::class.java)

        val gradesFragment = GradesFragment()
        val plusFragment = PlusFragment()
        val planFragment = PlanFragment()
        val courseFragment = CourseFragment()

        makeCurrentFragment(planFragment)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_oceny -> makeCurrentFragment(gradesFragment)
                R.id.ic_plan -> makeCurrentFragment(planFragment)
                R.id.ic_plus -> makeCurrentFragment(plusFragment)
                R.id.ic_przedmioty -> makeCurrentFragment(courseFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 123 && data != null) {
            Log.d("am2021", "Doszlo")
            val courseName = data.getStringExtra("courseName")
            if (courseName != null) {
                val teacher = data.getStringExtra("teacher")
                val pluses = data.getIntExtra("pluses", 0)
                if (teacher != null) {
                    viewModelDatabase.updateCourse(courseName, pluses, teacher)
                    if(pluses > 0){
                        viewModelDatabase.addPlusRow(PlusEntity(courseName, 0 ,0))
                    } else {
                        viewModelDatabase.deletePlusRow(courseName)
                    }
                }

                val courseSchedules = data.getSerializableExtra("schedule")
                Log.d("am2021", courseSchedules.toString())

                for (schedule in courseSchedules as ArrayList<ScheduleItem>) {
                    viewModelDatabase.updateLesson(courseName, converters.StringDayToInt(schedule.day), schedule.lesson.toInt())
                }

                val courseSchedulesToAdd = data.getSerializableExtra("scheduleToAdd")
                for (schedule in courseSchedulesToAdd as ArrayList<ScheduleItem>) {
                    viewModelDatabase.addLesson(LessonEntity(converters.StringDayToInt(schedule.day), courseName, schedule.lesson.toInt()))
                }

                val courseSchedulesToDelete = data.getSerializableExtra("scheduleToDelete")
                for (schedule in courseSchedulesToDelete as ArrayList<ScheduleItem>) {
                    viewModelDatabase.deleteLesson(schedule.lesson.toInt(), converters.StringDayToInt(schedule.day))
                }
                ToastMaker.makeSuccessToast(this, "Changes saved")
            }


        }
    }


}