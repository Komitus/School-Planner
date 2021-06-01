package com.example.mobileapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.data.Entities.GradeEntity
import com.example.mobileapp.databinding.SingleGradeRowBinding

class GradesDetailsAdapter(private val grades: ArrayList<GradeEntity>)  : RecyclerView.Adapter<GradesDetailsAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: SingleGradeRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradesDetailsAdapter.ViewHolder {
        return ViewHolder(SingleGradeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.gradeValue.text = grades[position].value.toString()
        holder.binding.gradeCategory.text = grades[position].category
        holder.binding.gradeDate.text = grades[position].date.toString()
    }

    override fun getItemCount(): Int {
        return grades.size
    }

}