package com.example.mobileapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.R
import com.example.mobileapp.data.PlanDay
import com.example.mobileapp.fragments.PlanFragment

class PlanAdapter(private val daysList: ArrayList<PlanDay>, private val mainActivity: PlanFragment) :
    RecyclerView.Adapter<PlanAdapter.ViewHolder>()  {


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val dayTextView : TextView = view.findViewById(R.id.dayTextView)
        val firstLessonTextView : TextView = view.findViewById(R.id.firstLessonTextView)
        val secondLessonTextView : TextView = view.findViewById(R.id.secondLessonTextView)
        val thirdLessonTextView : TextView = view.findViewById(R.id.thirdLessonTextView)
        val fourthLessonTextView : TextView = view.findViewById(R.id.fourthLessonTextView)
        val fifthLessonTextView : TextView = view.findViewById(R.id.fifthLessonTextView)
        val sixthLessonTextView : TextView = view.findViewById(R.id.sixthLessonTextView)
        val seventhLessonTextView : TextView = view.findViewById(R.id.seventhLessonTextView)
        val eighthLessonTextView : TextView = view.findViewById(R.id.eighthLessonTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.day_plan_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val day = daysList[position]
        holder.dayTextView.text = day.day.toString()
        holder.firstLessonTextView.text = day.lessons[0]
        holder.secondLessonTextView.text = day.lessons[1]
        holder.thirdLessonTextView.text = day.lessons[2]
        holder.fourthLessonTextView.text = day.lessons[3]
        holder.fifthLessonTextView.text = day.lessons[4]
        holder.sixthLessonTextView.text = day.lessons[5]
        holder.seventhLessonTextView.text = day.lessons[6]
        holder.eighthLessonTextView.text = day.lessons[7]

        //holder.view.setOnClickListener { editDay(holder.adapterPosition) }
    }


    override fun getItemCount(): Int {
        return daysList.size
    }
}