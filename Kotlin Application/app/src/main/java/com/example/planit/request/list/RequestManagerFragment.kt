package com.example.planit.request.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.planit.R
import com.example.planit.databinding.FragmentRequestManagerBinding
import com.example.planit.events.list.EventFragment
import com.example.planit.events.list.EventManagerViewModel

class RequestManagerFragment : Fragment() {
    lateinit var binding : FragmentRequestManagerBinding
    private lateinit var viewModel : EventManagerViewModel
    private lateinit var requestListFragment : EventFragment
    private val formCreateRequestFragment = FormCreateRequestFragment.newInstance {
        viewModel.launchCreateRequest(it)
        childFragmentManager
            .beginTransaction()
            .replace(R.id.requestListFrgtContainerView, requestListFragment)
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRequestManagerBinding.inflate(inflater, container, false)

        requestListFragment = childFragmentManager.findFragmentByTag("requestList") as EventFragment

        binding.btnAddRequest.setOnClickListener{
            childFragmentManager
                .beginTransaction()
                .replace(R.id.requestListFrgtContainerView, formCreateRequestFragment, "formRequest")
                .addToBackStack("formRequest")
                .commit()
        }

        viewModel = ViewModelProvider(this).get(EventManagerViewModel::class.java)

        viewModel.mutableListEvents.observe(viewLifecycleOwner){
            requestListFragment.replaceEventList(it)
        }

        viewModel.mutableNewRequestCreate.observe(viewLifecycleOwner){
            requestListFragment.addRequest(it)
        }

        viewModel.launchFetchRequest()

        return binding.root
    }

    companion object {
        fun newInstance() = RequestManagerFragment()
    }
}