package com.example.planit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.planit.databinding.FragmentSignInBinding
import com.example.planit.dtos.DtoOutputLogin


class SignInFragment : Fragment() {
    private lateinit var btnListener: ((DtoOutputLogin)-> Unit)
    lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        binding.btnSignInFragmentSignIn.setOnClickListener{
            val dto = DtoOutputLogin (binding.txtPlainSignIn.text.toString(), binding.txtPlainSignInPassword.text.toString())
            btnListener.invoke(dto)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(callback: ((DtoOutputLogin)-> Unit)) = SignInFragment().apply {
            btnListener = callback
        }
    }
}