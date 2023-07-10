package com.example.planit.events.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planit.MainAppActivity
import com.example.planit.R
import com.example.planit.dtos.DtoInputEvent

class EventFragment : Fragment() {
    private val eventsLists : ArrayList<DtoInputEvent> = arrayListOf()
    private val eventAdapter = EventsRecyclerViewAdapter(eventsLists)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.events_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)

                // Detect click on element
                adapter = eventAdapter.apply {
                    onItemClickListener = {
                        val act = activity as MainAppActivity
                        act.changeFragment(it)
                    }

                    onItemLongClickListener = {
                        val act = activity as MainAppActivity
                        act.changeFragment(it)
                    }
                }
            }
        }
        return view
    }

    fun replaceEventList(eventList:List<DtoInputEvent>){
        this.eventsLists.clear()
        this.eventsLists.addAll(eventList)
        eventAdapter.notifyDataSetChanged()
    }

    fun addRequest(dto : DtoInputEvent){
        this.eventsLists.add(dto)
        eventAdapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance() = EventFragment()
    }
}