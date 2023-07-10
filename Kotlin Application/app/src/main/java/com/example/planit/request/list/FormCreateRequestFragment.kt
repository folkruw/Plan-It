package com.example.planit.request.list

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.planit.databinding.FragmentFormCreateRequestBinding
import com.example.planit.dtos.DtoOutputEvent
import com.example.planit.utils.Session
import java.util.*


class FormCreateRequestFragment : Fragment() {
    private lateinit var binding : FragmentFormCreateRequestBinding
    private lateinit var btnListener : ((DtoOutputEvent) -> Unit)
    private val _session : Session = Session()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFormCreateRequestBinding.inflate(layoutInflater, container, false)

        binding.btnCreateEvent.setOnClickListener{
            var day: Int = binding.tdStartDate.dayOfMonth
            var month: Int = binding.tdStartDate.month
            var year: Int = binding.tdStartDate.year
            var calendar = Calendar.getInstance()
            calendar[year, month] = day
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val startDate: String = sdf.format(calendar.time)

            day = binding.tdEndDate.dayOfMonth
            month = binding.tdEndDate.month
            year = binding.tdEndDate.year
            calendar = Calendar.getInstance()
            calendar[year, month] = day
            var endDate: String = sdf.format(calendar.time)

            if(startDate > endDate){
                endDate = startDate
            }

            val dto = DtoOutputEvent(
                UUID.randomUUID().toString(),
                _session.getIDCompany(),
                _session.getID(),
                startDate + "T00:00:00",
                endDate + "T23:55:59",
                false,
                binding.txComments.text.toString(),
                "Absence"
            )
            btnListener.invoke(dto)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(callback : (DtoOutputEvent) -> Unit) = FormCreateRequestFragment().apply {
            btnListener = callback
        }
    }
}