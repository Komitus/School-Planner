package com.example.mobileapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.MainActivity
import com.example.mobileapp.data.Entities.LessonEntity
import com.example.mobileapp.data.Entities.PlanDay
import com.example.mobileapp.data.Entities.SubstitutionEntity
import com.example.mobileapp.data.dataBase.Converters
import com.example.mobileapp.databinding.DayPlanItemBinding
import java.time.LocalDate


class PlanAdapter(private val context : MainActivity) : RecyclerView.Adapter<PlanAdapter.ViewHolder>()  {

    private val daysList: ArrayList<PlanDay> = ArrayList<PlanDay>()

    init {
        var tmpDate = LocalDate.now()

        for(i in 0..4){
            if(tmpDate.dayOfWeek.value == 6){
                tmpDate = tmpDate.plusDays(2)
            } else if (tmpDate.dayOfWeek.value == 7) {
                tmpDate = tmpDate.plusDays(1)
            }
            daysList.add(PlanDay(tmpDate, Array(8) { null}))
            tmpDate = tmpDate.plusDays(1)
        }
    }

    inner class ViewHolder(val binding: DayPlanItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanAdapter.ViewHolder {
        return ViewHolder(DayPlanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val day = daysList[position]
        holder.binding.dayTextView.text = day.date.dayOfWeek.toString()+" "+day.date.toString()

        holder.binding.firstLessonTextView.text = day.lessons[0]?.courseName ?: ""
        holder.binding.secondLessonTextView.text = day.lessons[1]?.courseName ?: ""
        holder.binding.thirdLessonTextView.text = day.lessons[2]?.courseName ?: ""
        holder.binding.fourthLessonTextView.text = day.lessons[3]?.courseName ?: ""
        holder.binding.fifthLessonTextView.text = day.lessons[4]?.courseName ?: ""
        holder.binding.sixthLessonTextView.text = day.lessons[5]?.courseName ?: ""
        holder.binding.seventhLessonTextView.text = day.lessons[6]?.courseName ?: ""
        holder.binding.eighthLessonTextView.text = day.lessons[7]?.courseName ?: ""
    }


    override fun getItemCount(): Int {
        return daysList.size
    }

    fun setMyDaysList(list : List<LessonEntity>) {
        for (lesson in list) {
            for(i in 0..4){
                if(daysList[i].date.dayOfWeek.value == lesson.dayOfWeek){
                    daysList[i].lessons[lesson.lessonNumber-1] = lesson
                }
            }
        }
        notifyDataSetChanged()
    }

    fun setSubstitutions(list: List<SubstitutionEntity>){
        for(item in list){
            for(i in 0..4){
                if(daysList[i].date.dayOfWeek.value == item.dayOfWeek){
                    val prevLesson = daysList[i].lessons[item.lessonNumber-1]?.courseName ?: ""
                    if(!prevLesson.contains("->")){
                        daysList[i].lessons[item.lessonNumber-1] =
                            LessonEntity(item.dayOfWeek, "$prevLesson -> ${item.courseName}", item.lessonNumber)
                    }
                }
            }
        }
        notifyDataSetChanged()
    }

}