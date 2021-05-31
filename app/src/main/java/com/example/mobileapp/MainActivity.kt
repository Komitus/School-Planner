package com.example.mobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.actitvities.EditPlanActivity
import com.example.mobileapp.adapters.PlanAdapter
import com.example.mobileapp.data.PlanDay
import com.example.mobileapp.data.dataBase.Grades.CourseEntity
import com.example.mobileapp.data.dataBase.DBFactory
import com.example.mobileapp.data.dataBase.Grades.GradeEntity
import com.example.mobileapp.data.dataBase.PlannerDBViewModel
import com.example.mobileapp.fragments.CourseFragment
import com.example.mobileapp.fragments.GradesFragment
import com.example.mobileapp.fragments.PlanFragment
import com.example.mobileapp.fragments.PlusFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    lateinit var planAdapter: PlanAdapter
    lateinit var planManager : RecyclerView.LayoutManager
    lateinit var planRecyclerView: RecyclerView
    lateinit var viewModelDatabase: PlannerDBViewModel

    var planComponents : ArrayList<PlanDay> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelDatabase = ViewModelProvider(this, DBFactory(this.application)).get(PlannerDBViewModel::class.java)

        val course1  = CourseEntity(0, "Język Polski")
        val course2 = CourseEntity(0, "Matematyka")
        val course3 = CourseEntity(0, "Fizyka")
        viewModelDatabase.addCourse(course1)
        viewModelDatabase.addCourse(course2)
        viewModelDatabase.addCourse(course3)

        val gradesFragment = GradesFragment(viewModelDatabase)
        val plusFragment = PlusFragment()
        val planFragment = PlanFragment()
        val courseFragment = CourseFragment()


        makeCurrentFragment(gradesFragment)

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


    fun addGrade(view: View) {
        Toast.makeText(this, "dodaje ocene", Toast.LENGTH_SHORT).show()
    }

    fun editPlan(view: View) {
        Toast.makeText(this, "edytuje plan", Toast.LENGTH_SHORT).show()
    }

    fun addCourse(view: View) {
        Toast.makeText(this, "dodaje kurs", Toast.LENGTH_SHORT).show()
    }

    fun addPlus(view: View) {
        Toast.makeText(this, "dodaje plusa", Toast.LENGTH_SHORT).show()
    }


    fun editDay(position: Int) {
        val intent = Intent(this, EditPlanActivity::class.java)
        intent.putExtra("position", position)
        intent.putExtra("day", planComponents[position])
        startActivityForResult(intent, 1)
    }
}