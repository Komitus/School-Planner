package com.example.mobileapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.actitvities.AddCourseActivity
import com.example.mobileapp.data.ScheduleItem
import com.example.mobileapp.databinding.ScheduleItemBinding

class SimpleScheduleAdapter(private val scheduleItems : ArrayList<ScheduleItem>, val context : AddCourseActivity) : RecyclerView.Adapter<SimpleScheduleAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ScheduleItemBinding) : RecyclerView.ViewHolder(binding.root)
    private var selected = arrayListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ScheduleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
       return scheduleItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textView4.text = """${scheduleItems[position].day} ${scheduleItems[position].lesson}"""

        holder.itemView.setOnLongClickListener {
            context.buttonThrash.isVisible = true
            val itemPosition: Int = holder.layoutPosition
            if (selected.contains(itemPosition)) {
                if (selected.size == 1) {
                    context.buttonThrash.isVisible = false
                }
                selected.remove(itemPosition)
            } else {
                selected.add(itemPosition)
            }
            notifyItemChanged(itemPosition)
            return@setOnLongClickListener true
        }

        if(selected.contains(position)) holder.itemView.setBackgroundColor(Color.rgb(179, 45, 0))
        else holder.itemView.setBackgroundColor(Color.rgb(64, 128, 0))
    }

    fun getSelectedSchedules() : List<ScheduleItem> {
        val toReturn : ArrayList<ScheduleItem> = arrayListOf()
        for (index in selected) {
            toReturn.add(scheduleItems[index])
        }
        selected.clear()
        selected.trimToSize()
        return  toReturn.toList()
    }
}