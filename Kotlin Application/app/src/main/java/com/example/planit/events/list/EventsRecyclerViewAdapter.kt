package com.example.planit.events.list

import android.content.Intent
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planit.PlanIt
import com.example.planit.databinding.EventFragmentItemBinding
import com.example.planit.dtos.DtoInputEvent
import java.lang.Integer.parseInt
import java.util.*

class EventsRecyclerViewAdapter(
    private val values: List<DtoInputEvent>
) : RecyclerView.Adapter<EventsRecyclerViewAdapter.ViewHolder>() {

    var onItemClickListener: ((DtoInputEvent) -> Unit)? = null
    var onItemLongClickListener: ((DtoInputEvent) -> Unit)? = null

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

        // Object that contains information about an event
        val startDate = EventDate(
            parseInt(item.startDate.substring(8, 10)), // Day
            parseInt(item.startDate.substring(5, 7)),  // Month
            parseInt(item.startDate.substring(1, 4)),  // Year
            item.startDate.substring(11, 16)           // Hour
        )
        val endDate = EventDate(
            parseInt(item.endDate.substring(8, 10)),
            parseInt(item.endDate.substring(5, 7)),
            parseInt(item.endDate.substring(1, 4)),
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

        // Allow click on item
        init {
            binding.root.setOnClickListener{
                val item = values[bindingAdapterPosition]
                onItemClickListener?.invoke(item)
            }

            binding.root.setOnLongClickListener {
                val item = values[bindingAdapterPosition]
                onItemLongClickListener?.invoke(item)

                // Create calendar event
                val beginTime: Calendar = Calendar.getInstance()
                beginTime.set(Calendar.SECOND, 0)
                beginTime.set(Calendar.MINUTE, parseInt(item.startDate.split("T")[1].split(":")[1]))
                beginTime.set(Calendar.HOUR, parseInt(item.startDate.split("T")[1].split(":")[0]))
                beginTime.set(Calendar.AM_PM, Calendar.AM)
                beginTime.set(Calendar.MONTH, parseInt(item.startDate.split("T")[0].split("-")[1]) - 1)
                beginTime.set(Calendar.DAY_OF_MONTH, parseInt(item.startDate.split("T")[0].split("-")[2]))
                beginTime.set(Calendar.YEAR, parseInt(item.startDate.split("T")[0].split("-")[0]))

                val endTime: Calendar = Calendar.getInstance()
                endTime.set(Calendar.SECOND, 0)
                endTime.set(Calendar.MINUTE, parseInt(item.endDate.split("T")[1].split(":")[1]))
                endTime.set(Calendar.HOUR, parseInt(item.endDate.split("T")[1].split(":")[0]))
                endTime.set(Calendar.AM_PM, Calendar.AM)
                endTime.set(Calendar.MONTH, parseInt(item.endDate.split("T")[0].split("-")[1]) - 1)
                endTime.set(Calendar.DAY_OF_MONTH, parseInt(item.endDate.split("T")[0].split("-")[2]))
                endTime.set(Calendar.YEAR, parseInt(item.endDate.split("T")[0].split("-")[0]))

                val intent = Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.timeInMillis)
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.timeInMillis)
                    .putExtra(CalendarContract.Events.TITLE, item.types)
                    .putExtra(CalendarContract.Events.DESCRIPTION, item.comments)
                    .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                    .putExtra(CalendarContract.Events.ALL_DAY, false)

                PlanIt.getContext().startActivity(intent)
                true
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