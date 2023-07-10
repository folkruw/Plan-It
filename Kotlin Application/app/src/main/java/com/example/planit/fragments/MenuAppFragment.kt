package com.example.planit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.planit.databinding.FragmentMenuAppBinding

class MenuAppFragment : Fragment() {
    private lateinit var btnListener: ((Int)-> Unit)
    lateinit var binding: FragmentMenuAppBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuAppBinding.inflate(layoutInflater, container, false)
        binding.rlEventsLists.setOnClickListener{
            btnListener.invoke(1)
        }
        binding.rlRequest.setOnClickListener{
            btnListener.invoke(3)
        }
        binding.rlAnnouncements.setOnClickListener{
            btnListener.invoke(4)
        }
        binding.rlProfil.setOnClickListener{
            btnListener.invoke(5)
        }
        binding.rlLeave.setOnClickListener{
            btnListener.invoke(6)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(callback: ((Int) -> Unit)) = MenuAppFragment().apply {
            btnListener = callback
        }
    }
}