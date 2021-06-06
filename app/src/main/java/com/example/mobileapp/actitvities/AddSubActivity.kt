package com.example.mobileapp.actitvities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mobileapp.R
import com.example.mobileapp.data.Entities.SubstitutionEntity
import com.example.mobileapp.databinding.ActivityAddSubBinding
import com.example.mobileapp.toasts.ToastMaker
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class AddSubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddSubBinding
    private lateinit var datePickerDialog : DatePickerDialog
    private lateinit var dateForReturn : LocalDate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSubBinding.inflate(layoutInflater)

        initDatePicker()
        dateForReturn = LocalDate.now()
        binding.dateButtonSub.text = LocalDate.now().toString()

        val list = intent.getStringArrayListExtra("listOfCourses") as ArrayList<String>
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        binding.courseSubSpinner.adapter = arrayAdapter

        binding.courseSubSpinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                (parent!!.getChildAt(0) as TextView).setTextColor(Color.BLUE)
                (parent.getChildAt(0) as TextView).textSize = 30f
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding.lessonNumbSpinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                (parent!!.getChildAt(0) as TextView).setTextColor(Color.BLUE)
                (parent.getChildAt(0) as TextView).textSize = 30f
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        setContentView(binding.root)
    }

    private fun initDatePicker(){
        val dateSetListener : DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener {
                _, year, month, dayOfMonth ->
            val tmp =  LocalDate.of(year, month+1, dayOfMonth)
            dateForReturn = tmp
            binding.dateButtonSub.text = tmp.toString()
        }

        val cal : Calendar = Calendar.getInstance()
        val year : Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH)
        val day : Int = cal.get(Calendar.DAY_OF_MONTH)

        val style : Int = R.style.my_dialog_theme

        datePickerDialog = DatePickerDialog(this, style,dateSetListener, year, month, day)
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
    }

    fun openDatePicker(view: View) {
        datePickerDialog.show()
    }

    fun onSubmitClick(view: View){
        val day = dateForReturn.dayOfWeek.value
        if(day != 7 && day != 6){
            val returnIntent = Intent()
            val retVal = SubstitutionEntity(0, day,
                binding.courseSubSpinner.selectedItem.toString(),
                binding.lessonNumbSpinner.selectedItem.toString().toInt(),
                dateForReturn
            )
            returnIntent.putExtra("subst", retVal)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        } else {
            ToastMaker.makeErrorToast(this, "Choose school day,\n not weekend")
        }

    }
}