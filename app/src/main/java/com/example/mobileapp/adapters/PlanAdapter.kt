package com.example.mobileapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.MainActivity
import com.example.mobileapp.data.Entities.LessonEntity
import com.example.mobileapp.data.Entities.PlanDay
import com.example.mobileapp.data.dataBase.Converters
import com.example.mobileapp.databinding.DayPlanItemBinding


class PlanAdapter(private val context : MainActivity) : RecyclerView.Adapter<PlanAdapter.ViewHolder>()  {
    var daysList : Array<PlanDay>
    private val converters : Converters = Converters()
    init {
            daysList = arrayOf(
                PlanDay(converters.intToDay(1), Array(8) { null}),
                PlanDay(converters.intToDay(2), Array(8) { null}),
                PlanDay(converters.intToDay(3), Array(8) { null}),
                PlanDay(converters.intToDay(4), Array(8) { null}),
                PlanDay(converters.intToDay(5), Array(8) { null})
            )

    }

    inner class ViewHolder(val binding: DayPlanItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanAdapter.ViewHolder {
        return ViewHolder(DayPlanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val day = daysList[position]
        holder.binding.dayTextView.text = day.day.toString()

            holder.binding.firstLessonTextView.text = day.lessons[0]?.courseName ?: "----"
            holder.binding.secondLessonTextView.text = day.lessons[1]?.courseName ?: "----"
            holder.binding.thirdLessonTextView.text = day.lessons[2]?.courseName ?: "----"
            holder.binding.fourthLessonTextView.text = day.lessons[3]?.courseName ?: "----"
            holder.binding.fifthLessonTextView.text = day.lessons[4]?.courseName ?: "----"
            holder.binding.sixthLessonTextView.text = day.lessons[5]?.courseName ?: "----"
            holder.binding.seventhLessonTextView.text = day.lessons[6]?.courseName ?: "----"
            holder.binding.eighthLessonTextView.text = day.lessons[7]?.courseName ?: "----"
    }


    override fun getItemCount(): Int {
        return daysList.size
    }

    fun setMyDaysList(list : List<LessonEntity>) {
        for (lesson in list) {
            daysList[lesson.dayOfWeek-1].lessons[lesson.lessonNumber-1] = lesson
        }


        notifyDataSetChanged()
    }

}