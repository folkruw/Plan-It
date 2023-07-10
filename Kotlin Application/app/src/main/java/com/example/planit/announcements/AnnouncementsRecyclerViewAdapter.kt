package com.example.planit.announcements

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planit.databinding.FragmentAnnouncementsBinding
import com.example.planit.dtos.DtoInputAnnouncements

class AnnouncementsRecyclerViewAdapter(
    private val values: List<DtoInputAnnouncements>
) : RecyclerView.Adapter<AnnouncementsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentAnnouncementsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.content.text = item.content
        holder.function.text = if(item.idFunctions == 5) "Directeur" else "Employé"
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentAnnouncementsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val function: TextView = binding.tvFunction
        val content: TextView = binding.tvContent
    }
}
