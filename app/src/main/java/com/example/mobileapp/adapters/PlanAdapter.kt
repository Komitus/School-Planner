package com.example.mobileapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.data.Entities.PlanDay
import com.example.mobileapp.databinding.DayPlanItemBinding


class PlanAdapter(private val daysList: ArrayList<PlanDay>) : RecyclerView.Adapter<PlanAdapter.ViewHolder>()  {

    inner class ViewHolder(val binding: DayPlanItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanAdapter.ViewHolder {
        return ViewHolder(DayPlanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val day = daysList[position]
        holder.binding.dayTextView.text = day.day.toString()
        holder.binding.firstLessonTextView.text = day.lessons[0]
        holder.binding.secondLessonTextView.text = day.lessons[1]
        holder.binding.thirdLessonTextView.text = day.lessons[2]
        holder.binding.fourthLessonTextView.text = day.lessons[3]
        holder.binding.fifthLessonTextView.text = day.lessons[4]
        holder.binding.sixthLessonTextView.text = day.lessons[5]
        holder.binding.seventhLessonTextView.text = day.lessons[6]
        holder.binding.eighthLessonTextView.text = day.lessons[7]

        //holder.view.setOnClickListener { editDay(holder.adapterPosition) }
    }


    override fun getItemCount(): Int {
        return daysList.size
    }
}