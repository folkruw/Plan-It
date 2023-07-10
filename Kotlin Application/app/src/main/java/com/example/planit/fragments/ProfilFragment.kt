package com.example.planit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.planit.databinding.FragmentProfilBinding
import com.example.planit.utils.Session

class ProfilFragment : Fragment() {
    private lateinit var binding : FragmentProfilBinding
    private val _session : Session = Session()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilBinding.inflate(layoutInflater, container, false)

        binding.tvProfilId.text = _session.getID().toString()
        binding.tvProfilFirstName.text = _session.info().get(0)
        binding.tvProfilName.text = _session.info().get(1)

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfilFragment()
    }
}