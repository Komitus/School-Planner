package com.example.mobileapp.actitvities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.mobileapp.R
import com.example.mobileapp.data.PlanDay

class EditPlanActivity : AppCompatActivity() {

    lateinit var day : PlanDay
     var position : Int = 0
    val lessons : Array<EditText> = arrayOf(
        findViewById(R.id.firstLessonEditText),
        findViewById(R.id.secondLessonEditText),
        findViewById(R.id.thirdLessonEditText),
        findViewById(R.id.fourthLessonEditText),
        findViewById(R.id.fifthLessonEditText),
        findViewById(R.id.sixthLessonEditText),
        findViewById(R.id.seventhLessonEditText),
        findViewById(R.id.eighthLessonEditText)
    )
    val first : EditText = findViewById(R.id.firstLessonEditText)
    val second : EditText = findViewById(R.id.secondLessonEditText)
    val third : EditText = findViewById(R.id.thirdLessonEditText)
    val fourth : EditText = findViewById(R.id.fourthLessonEditText)
    val fifth : EditText = findViewById(R.id.fifthLessonEditText)
    val sixth : EditText = findViewById(R.id.sixthLessonEditText)
    val seventh : EditText =  findViewById(R.id.seventhLessonEditText)
    val eighth : EditText = findViewById(R.id.eighthLessonEditText)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_plan)

        position = intent.getIntExtra("position", 0)
        day = intent.getSerializableExtra("day") as PlanDay

        findViewById<TextView>(R.id.dayTextView).text = day.day.toString()
        for (i in 0..7) {
            lessons[i].hint = day.lessons[i]
        }
    }

    fun confirm(view: View) {
        var final : Array<String> = arrayOf()
        for (i in 0..7) {
            if (lessons[i].text.isEmpty()) {
                final[i] = "-----"
            } else {
                final[i] = lessons[i].text.toString()
            }
        }
        val finalDay = PlanDay(day.day, final)
        val intent = Intent()
        intent.putExtra("position", position)
        intent.putExtra("day", finalDay)
        setResult(Activity.RESULT_OK, intent)
        finish()

    }
}