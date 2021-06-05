package com.example.mobileapp.actitvities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.MainActivity
import com.example.mobileapp.R
import com.example.mobileapp.adapters.SimpleScheduleAdapter
import com.example.mobileapp.data.ScheduleItem
import com.example.mobileapp.toasts.ToastMaker

class AddCourseActivity : AppCompatActivity() {

    private lateinit var courseName : EditText
    private lateinit var spinnerPluses : Spinner
    private lateinit var spinnerDays : Spinner
    private lateinit var spinnerLessons : Spinner
    private lateinit var teacherEditText: EditText
    private lateinit var butotnOk : Button
    private lateinit var buttonDone : Button
    private lateinit var scheduleRecyclerView: RecyclerView
    private lateinit var scheduleAdapter : SimpleScheduleAdapter
    private lateinit var scheduleManager: LinearLayoutManager
    private var scheduleItems : ArrayList<ScheduleItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        courseName = findViewById(R.id.courseNameEditText)
        spinnerPluses = findViewById(R.id.spinner)
        spinnerDays = findViewById(R.id.spinner2)
        spinnerLessons = findViewById(R.id.spinner3)
        teacherEditText = findViewById(R.id.editTextTextPersonName)
        butotnOk = findViewById(R.id.buttonOk)
        buttonDone = findViewById(R.id.buttonDone)
        scheduleRecyclerView = findViewById(R.id.recyclerViewSchedule)
        scheduleAdapter = SimpleScheduleAdapter(scheduleItems)
        scheduleManager = LinearLayoutManager(this)
        scheduleRecyclerView.apply {
            setHasFixedSize(true)
            adapter = scheduleAdapter
            layoutManager = scheduleManager
        }

        ArrayAdapter.createFromResource(
                this,
                R.array.days,
                android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_list_item_1)
            spinnerDays.adapter = it
        }

        ArrayAdapter.createFromResource(
                this,
                R.array.lessons,
                android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_list_item_1)
            spinnerLessons.adapter = it
        }

        ArrayAdapter.createFromResource(
                this,
                R.array.pluses,
                android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_list_item_1)
            spinnerPluses.adapter = it
        }

        butotnOk.setOnClickListener {
            val scheduleItem = ScheduleItem(spinnerDays.selectedItem.toString(), spinnerLessons.selectedItem.toString())
            if (scheduleItems.contains(scheduleItem)) {
                ToastMaker.makeErrorToast(this, "There cannot be 2 lessons at one time")
            } else {
                scheduleItems.add(scheduleItem)
                scheduleAdapter.notifyDataSetChanged()
            }
        }


    }

    fun addCourse(view: View) {

        if  (courseName.text.isNotBlank()) {
            val intent = Intent()
            intent.putExtra("courseName", courseName.text.toString())
            intent.putExtra("teacher", teacherEditText.text.toString())
            intent.putExtra("pluses", spinnerPluses.selectedItem.toString().toInt())
            intent.putExtra("schedule", scheduleItems)
            setResult(Activity.RESULT_OK, intent)
            finish()
        } else {
            ToastMaker.makeErrorToast(this, "Type course name!")
        }


    }
}