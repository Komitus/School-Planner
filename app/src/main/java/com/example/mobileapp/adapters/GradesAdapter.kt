package com.example.mobileapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.MainActivity
import com.example.mobileapp.actitvities.GradesDetailsActivity
import com.example.mobileapp.data.Entities.CourseEntity
import com.example.mobileapp.data.Entities.GradeEntity
import com.example.mobileapp.databinding.GradesForCourseRowBinding
import kotlin.math.roundToInt

class GradesAdapter(private val context: MainActivity) : RecyclerView.Adapter<GradesAdapter.ViewHolder>(){

    //in second arraylist in its pair first is id of grade, second - value
    var grades = ArrayList<Pair<CourseEntity, ArrayList<GradeEntity>>>()
    inner class ViewHolder(val binding: GradesForCourseRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradesAdapter.ViewHolder {
        return ViewHolder(GradesForCourseRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        if(grades[position].second.isNotEmpty()){
            holder.binding.lastGrades.text = gradesToString(grades[position].second)
            holder.binding.meanForCourse.text = calculateMean(grades[position].second)
        }
        holder.binding.courseGrades.text = grades[position].first.name

        holder.itemView.setOnClickListener {
            val gradesDetailIntent = Intent(context, GradesDetailsActivity::class.java)
            gradesDetailIntent.putExtra("courseName", grades[position].first.name)
            gradesDetailIntent.putExtra("courseId", grades[position].first.id)
            context.startActivity(gradesDetailIntent)
        }

    }

    private fun calculateMean(gradesList: ArrayList<GradeEntity>): String {
        var mean = 0f
        for(i in 0..gradesList.lastIndex){
            mean += gradesList[i].value!!
        }
        mean /= gradesList.size.toFloat()
        return ((mean * 100).roundToInt().toFloat()/100f).toString()
    }

    override fun getItemCount(): Int {
        return grades.size
    }

    fun setCourses(passedList: List<CourseEntity>) {
        for(i in 0..passedList.lastIndex){
            if(!grades.any{ it.first.id == passedList[i].id}) {
                grades.add(Pair(passedList[i], ArrayList()))
                //notifyItemInserted(itemCount)
            }
        }
        notifyDataSetChanged()
    }

    fun setList(passedList: List<GradeEntity>){
        grades.forEach { it.second.clear() }
        for(passed in passedList){
            for(i in 0..grades.lastIndex){
                //val tmp = Pair(passed.id, passed.value)
                if(grades[i].first.id == passed.course) {
                    //if(!grades[i].second.contains(tmp)){
                        grades[i].second.add(0, passed)
                        //notifyItemChanged(i)
                    //}
                }
            }
        }
        notifyDataSetChanged()
    }

    private fun gradesToString(arrayList: ArrayList<GradeEntity>): String{
        var toRet = ""
        for(value in arrayList){
            toRet = toRet + value.value.toString()+", "
        }
        return toRet
    }

}