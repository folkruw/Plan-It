package com.example.planit.request.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planit.databinding.EventFragmentItemBinding
import com.example.planit.dtos.DtoInputEvent

class RequestRecyclerViewAdapter(
    private val values: List<DtoInputEvent>
) : RecyclerView.Adapter<RequestRecyclerViewAdapter.ViewHolder>() {

    var onItemClickListener: ((DtoInputEvent) -> Unit)? = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            EventFragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        val startDate = EventDate(
            Integer.parseInt(item.startDate.substring(8, 10)),
            Integer.parseInt(item.startDate.substring(5, 7)),
            Integer.parseInt(item.startDate.substring(1, 4)),
            item.startDate.substring(11, 16)
        )
        val endDate = EventDate(
            Integer.parseInt(item.endDate.substring(8, 10)),
            Integer.parseInt(item.endDate.substring(5, 7)),
            Integer.parseInt(item.endDate.substring(1, 4)),
            item.endDate.substring(11, 16)
        )

        if(startDate.day != endDate.day) {
            holder.startDate.text = startDate.day.toString() + "-" + endDate.day.toString()
        } else {
            holder.startDate.text = startDate.day.toString()
        }
        holder.hour.text = startDate.hour + " - " + endDate.hour
        holder.types.text = item.types
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: EventFragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val startDate: TextView = binding.tvDay
        val hour: TextView = binding.tvHour
        val types : TextView = binding.tvTypes

        init {
            binding.root.setOnClickListener{
                val item = values[bindingAdapterPosition]
                onItemClickListener?.invoke(item)
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + startDate.text + "'"
        }
    }

}

data class EventDate(
    var day : Int,
    val month : Int,
    val year : Int,
    val hour : String
)