package com.example.mobileapp.actitvities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileapp.adapters.GradesDetailsAdapter
import com.example.mobileapp.data.Entities.GradeEntity
import com.example.mobileapp.databinding.ActivityGradesDetailsBinding

class GradesDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGradesDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGradesDetailsBinding.inflate(layoutInflater)
        val view = binding.root

        binding.gradesDetailsCourseName.text = intent.getStringExtra("courseName")
        val adapter = GradesDetailsAdapter(intent.getSerializableExtra("grades") as ArrayList<GradeEntity>)

        binding.gradesDetailsRecycler.adapter = adapter
        binding.gradesDetailsRecycler.layoutManager = LinearLayoutManager(this)

        setContentView(view)
    }

}