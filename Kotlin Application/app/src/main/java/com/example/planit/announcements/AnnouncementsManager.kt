package com.example.planit.announcements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.planit.databinding.FragmentAnnouncementsManagerBinding

class AnnouncementsManagerFragment : Fragment() {
    lateinit var binding : FragmentAnnouncementsManagerBinding
    private lateinit var viewModel : AnnouncementsManagerViewModel
    private lateinit var announcementsListFragment : AnnouncementsFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnnouncementsManagerBinding.inflate(inflater, container, false)

        announcementsListFragment = childFragmentManager.findFragmentByTag("frgmt_Announcements") as AnnouncementsFragment

        viewModel = ViewModelProvider(this).get(AnnouncementsManagerViewModel::class.java)

        viewModel.mutableListAnnouncements.observe(viewLifecycleOwner){
            announcementsListFragment.replaceAnnouncementsList(it)
        }

        viewModel.launchFetchAllAnnouncements()

        return binding.root
    }

    companion object {
        fun newInstance() = AnnouncementsManagerFragment()
    }
}