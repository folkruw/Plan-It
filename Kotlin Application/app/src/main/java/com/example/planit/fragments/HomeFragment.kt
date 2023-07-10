package com.example.planit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.planit.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var  btnListener: (()-> Unit)?  = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)


        binding.btnHomeFragmentSignIn.setOnClickListener{
            btnListener?.invoke()
        }

        return binding.root
    }

    companion object {
        lateinit var binding: FragmentHomeBinding
        @JvmStatic
        fun newInstance(callback: (()-> Unit)) = HomeFragment().apply {
            this.btnListener=callback
        }
    }
}