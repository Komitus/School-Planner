package com.example.mobileapp.actitvities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.mobileapp.MainActivity
import com.example.mobileapp.R
import com.example.mobileapp.toasts.ToastMaker

class AddCourseActivity : AppCompatActivity() {

    private lateinit var finishButton : Button
    private lateinit var courseName : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        finishButton = findViewById(R.id.buttonFinishAdding)
        courseName = findViewById(R.id.courseNameEditText)



    }

    fun addCourse(view: View) {

        if  (courseName.text.isNotBlank()) {
            val intent = Intent()
            intent.putExtra("courseName", courseName.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        } else {
            ToastMaker.makeErrorToast(this, "Type course name!")
        }


    }
}