package com.example.planit.request.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planit.R
import com.example.planit.dtos.DtoInputEvent

class RequestFragment : Fragment() {
    private val eventsLists : ArrayList<DtoInputEvent> = arrayListOf()
    private val eventAdapter = RequestRecyclerViewAdapter(eventsLists)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.events_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = eventAdapter.apply {
                    onItemClickListener = {
                    }
                }
            }
        }
        return inflater.inflate(R.layout.fragment_request, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = RequestFragment().apply{}
    }
}