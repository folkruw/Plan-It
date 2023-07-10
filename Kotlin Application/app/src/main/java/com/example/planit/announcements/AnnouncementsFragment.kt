package com.example.planit.announcements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planit.R
import com.example.planit.dtos.DtoInputAnnouncements

class AnnouncementsFragment : Fragment() {
    private val announcementsLists : ArrayList<DtoInputAnnouncements> = arrayListOf()
    private val announcementsAdapter = AnnouncementsRecyclerViewAdapter(announcementsLists)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_announcements_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = announcementsAdapter.apply {

                }
            }
        }
        return view
    }

    fun replaceAnnouncementsList(announcementsList:List<DtoInputAnnouncements>){
        this.announcementsLists.clear()
        this.announcementsLists.addAll(announcementsList)
        announcementsAdapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance() = AnnouncementsFragment()
    }
}