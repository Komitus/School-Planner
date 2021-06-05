package com.example.mobileapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.data.ScheduleItem
import com.example.mobileapp.databinding.CourseItemBinding
import com.example.mobileapp.databinding.ScheduleItemBinding

class SimpleScheduleAdapter(val scheduleItems : ArrayList<ScheduleItem>) : RecyclerView.Adapter<SimpleScheduleAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ScheduleItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ScheduleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
       return scheduleItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textView4.text = """${scheduleItems[position].day} ${scheduleItems[position].lesson}"""
    }
}