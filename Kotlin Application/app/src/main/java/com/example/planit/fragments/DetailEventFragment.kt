package com.example.planit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.planit.databinding.FragmentDetailEventBinding
import com.example.planit.dtos.DtoInputEvent

class DetailEventFragment(dto: DtoInputEvent) : Fragment() {
    lateinit var binding: FragmentDetailEventBinding
    lateinit var item : DtoInputEvent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailEventBinding.inflate(layoutInflater, container, false)
        binding.tvStartDate.text = item.startDate.substring(0, 10)
        binding.tvEndDate.text = item.endDate.substring(0, 10)

        binding.tvStartDateHour.text = item.startDate.substring(11, 16)
        binding.tvEndDateHour.text = item.endDate.substring(11, 16)

        binding.tvTypes.text = item.types
        binding.tvDesc.text = item.comments
        binding.tvValid.text = if(item.isValid) "Validé" else "Non validé"
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(dto: DtoInputEvent) = DetailEventFragment(dto).apply {
            item = dto
        }
    }

}