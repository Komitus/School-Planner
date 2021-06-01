package com.example.mobileapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mobileapp.data.Entities.CourseEntity
import com.example.mobileapp.data.Entities.GradeEntity
import com.example.mobileapp.data.dataBase.DBFactory
import com.example.mobileapp.data.dataBase.PlannerDBViewModel
import com.example.mobileapp.fragments.CourseFragment
import com.example.mobileapp.fragments.GradesFragment
import com.example.mobileapp.fragments.PlanFragment
import com.example.mobileapp.fragments.PlusFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    lateinit var viewModelDatabase: PlannerDBViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelDatabase = ViewModelProvider(this.viewModelStore,
            DBFactory(this.application)).get(PlannerDBViewModel::class.java)


        viewModelDatabase.addCourse(CourseEntity(0, "Polski", "JP"))
        viewModelDatabase.addGrade(GradeEntity(0, 5, "kartkówka", LocalDate.now(), 1))

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
}