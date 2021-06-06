package com.example.mobileapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp.data.Entities.PlusEntity
import com.example.mobileapp.data.dataBase.PlannerDBViewModel
import com.example.mobileapp.databinding.PlusRowBinding

class PlusAdapter(private val dbRef: PlannerDBViewModel) : RecyclerView.Adapter<PlusAdapter.ViewHolder>() {

    private var pluses = listOf<PlusEntity>()

    inner class ViewHolder(val binding: PlusRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlusAdapter.ViewHolder {
        return ViewHolder(PlusRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.plusesCourseName.text = pluses[position].courseName
        holder.binding.plusActual.text = pluses[position].actual.toString()
        holder.binding.plusUsed.text = pluses[position].used.toString()
        holder.binding.addPlus.setOnClickListener {
            dbRef.addPlusWithCheck(pluses[position].courseName)
        }
        holder.binding.deletePlus.setOnClickListener {
            dbRef.deleteOnePlus(pluses[position].courseName)
        }
    }

    override fun getItemCount(): Int {
        return pluses.size
    }

    fun setPluses(plusesList: List<PlusEntity>){
        pluses = plusesList
        notifyDataSetChanged()
    }


}