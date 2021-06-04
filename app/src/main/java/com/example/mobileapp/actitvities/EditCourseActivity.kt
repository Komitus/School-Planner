package com.example.mobileapp.actitvities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mobileapp.R

class EditCourseActivity : AppCompatActivity() {

    private lateinit var courseNameTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_course)
        courseNameTextView = findViewById(R.id.courseNameTV)
        courseNameTextView.text = intent.getStringExtra("courseName")
    }
}