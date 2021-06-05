package com.example.mobileapp.actitvities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileapp.R
import com.example.mobileapp.data.Entities.GradeEntity
import com.example.mobileapp.databinding.ActivityAddGradeBinding
import com.example.mobileapp.toasts.ToastMaker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate
import java.util.*


class AddGradeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddGradeBinding
    private var intentMode: String? = "add"
    private var courseId: Int = 0
    private var chosenGrade : Int? = null
    private var gradeId = 0
    private lateinit var datePickerDialog : DatePickerDialog
    private lateinit var dateForReturn : LocalDate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGradeBinding.inflate(layoutInflater)
        val view = binding.root

        initDatePicker()
        dateForReturn = LocalDate.now()
        intentMode = intent.getStringExtra("intentMode")

        if(intentMode == "edit"){
            val receivedGrade = intent.getSerializableExtra("grade") as GradeEntity
            courseId = receivedGrade.course!!
            binding.dateButton.text = receivedGrade.date.toString()
            binding.addGradeSpinner.setSelection(receivedGrade.value!!.minus(1))
            gradeId = receivedGrade.id
            binding.addGradeCategory.setText(receivedGrade.category)

        } else {
            courseId = intent.getIntExtra("courseId", 0)
            binding.dateButton.text = LocalDate.now().toString()
            binding.addGradeSpinner.setSelection(2)
        }

        binding.addGradeSpinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                (parent!!.getChildAt(0) as TextView).setTextColor(Color.BLUE)
                (parent!!.getChildAt(0) as TextView).textSize = 50f
                chosenGrade = position+1
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        setContentView(view)
    }

    private fun initDatePicker(){
        var dateSetListener : DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener {
                _, year, month, dayOfMonth ->
            val tmp =  LocalDate.of(year, month+1, dayOfMonth)
            dateForReturn = tmp
            binding.dateButton.text = tmp.toString()
        }

        val cal : Calendar = Calendar.getInstance()
        val year : Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH)
        val day : Int = cal.get(Calendar.DAY_OF_MONTH)

        val style : Int = R.style.my_dialog_theme

        datePickerDialog = DatePickerDialog(this, style,dateSetListener, year, month, day)
    }

    fun openDatePicker(view: View) {
        datePickerDialog.show()
    }

    fun onSubmitClick(view: View){

        if(binding.addGradeCategory.text.isNotEmpty() && chosenGrade != null)
        {
            val returnIntent = Intent(this, GradesDetailsActivity::class.java)
            val grade = GradeEntity(gradeId, chosenGrade, binding.addGradeCategory.text.toString(), dateForReturn, courseId)
            returnIntent.putExtra("grade", grade)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
        else
            ToastMaker.makeErrorToast(this, "Fill every input")
    }
}