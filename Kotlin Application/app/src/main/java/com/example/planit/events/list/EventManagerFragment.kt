package com.example.planit.events.list

import android.icu.text.DateFormat
import android.icu.text.DateFormatSymbols
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.planit.databinding.FragmentEventManagerBinding
import java.util.*

class EventManagerFragment : Fragment() {
    lateinit var binding : FragmentEventManagerBinding
    private lateinit var viewModel : EventManagerViewModel
    private lateinit var eventsListFragment : EventFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventManagerBinding.inflate(inflater, container, false)

        val dateFormat: DateFormat = SimpleDateFormat("MM")
        val date = Date()
        binding.tvMonth.text = DateFormatSymbols(Locale.FRENCH).months[Integer.parseInt(dateFormat.format(date))-1].uppercase()

        eventsListFragment = childFragmentManager.findFragmentByTag("eventsList") as EventFragment

        viewModel = ViewModelProvider(this).get(EventManagerViewModel::class.java)

        viewModel.mutableListEvents.observe(viewLifecycleOwner){
            eventsListFragment.replaceEventList(it)
        }

        viewModel.launchFetchAllEvents()

        return binding.root
    }

    companion object {
        fun newInstance() = EventManagerFragment()
    }
}