package com.example.mobileapp.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.actitvities.GradesDetailsActivity
import com.example.mobileapp.data.Entities.GradeEntity
import com.example.mobileapp.databinding.SingleGradeRowBinding

class GradesDetailsAdapter(private val context: GradesDetailsActivity) : RecyclerView.Adapter<GradesDetailsAdapter.ViewHolder>(){

    private var grades = listOf<GradeEntity>()
    private var selected = arrayListOf<Int>()
    inner class ViewHolder(val binding: SingleGradeRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradesDetailsAdapter.ViewHolder {
        return ViewHolder(SingleGradeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.gradeValue.text = grades[position].value.toString()
        holder.binding.gradeCategory.text = grades[position].category
        holder.binding.gradeDate.text = grades[position].date.toString()

        holder.itemView.setOnLongClickListener{
                context.trashButton.isVisible = true
                val itemPosition: Int = holder.layoutPosition
                if (selected.contains(itemPosition)) {
                    if (selected.size == 1) {
                        context.trashButton.isVisible = false
                    }
                    selected.remove(itemPosition)
                } else {
                    selected.add(itemPosition)
                }
                notifyItemChanged(itemPosition)
            return@setOnLongClickListener true
        }
        holder.itemView.setOnClickListener{
            context.editGrade(grades[position])
        }
        if(selected.contains(position)) holder.itemView.setBackgroundColor(Color.DKGRAY);
        else holder.itemView.setBackgroundColor(Color.WHITE)
    }

    override fun getItemCount(): Int {
        return grades.size
    }

    fun setGrades(passedGrades: List<GradeEntity>){
        grades = passedGrades.sortedByDescending { it.date }
        notifyDataSetChanged()
    }

    fun getSelectedGradesIds(): List<Int>{
        var toRet = ArrayList<Int>()
        for(index in selected){
            toRet.add(grades[index].id)
        }
        selected.clear()
        selected.trimToSize()
        return  toRet.toList()
    }

}