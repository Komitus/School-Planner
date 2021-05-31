package com.example.mobileapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.data.dataBase.Grades.CourseEntity
import com.example.mobileapp.data.dataBase.Grades.GradeEntity
import com.example.mobileapp.databinding.GradeItemBinding
import kotlin.collections.ArrayList

class GradesAdapter : RecyclerView.Adapter<GradesAdapter.ViewHolder>(){

    var grades = ArrayList<Pair<CourseEntity, ArrayList<Pair<Int, Int>>>>()
    inner class ViewHolder(val binding: GradeItemBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradesAdapter.ViewHolder {
        return ViewHolder(GradeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.binding.courseAbbreviation.text = grades.get(position).first.name[0].toString() + grades[position].first.name[1]
            if(grades[position].second.isNotEmpty()){
                holder.binding.lastGrades.text = gradesToString(grades[position].second)
            }
            holder.binding.courseGrades.text = grades[position].first.name

    }
    override fun getItemCount(): Int {
        return grades.size
    }

    fun setCourses(passedList: List<CourseEntity>) {
        for(i in 0..passedList.lastIndex){
            if(!grades.any{ it.first.id == passedList[i].id}) {
                grades.add(Pair(passedList[i], ArrayList()))
                notifyItemInserted(itemCount)
            }
        }
    }

    fun setList(passedList: List<GradeEntity>){
        grades.forEach { it.second.clear() }
        for(passed in passedList){
            for(i in 0..grades.lastIndex){
                val tmp = Pair(passed.id, passed.value)
                if(grades[i].first.id == passed.course) {
                    //if(!grades[i].second.contains(tmp)){
                        grades[i].second.add(tmp)
                        notifyItemChanged(i)
                    //}
                }
            }
        }
    }

    private fun gradesToString(arrayList: ArrayList<Pair<Int, Int>>): String{
        var toRet = ""
        for(value in arrayList){
            toRet = toRet + value.second.toString()+", "
        }
        return toRet
    }



}