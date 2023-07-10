package com.example.planit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.planit.announcements.AnnouncementsManagerFragment
import com.example.planit.dtos.DtoInputEvent
import com.example.planit.events.list.EventManagerFragment
import com.example.planit.fragments.DetailEventFragment
import com.example.planit.fragments.MenuAppFragment
import com.example.planit.fragments.ProfilFragment
import com.example.planit.request.list.RequestManagerFragment
import com.example.planit.utils.Session

class MainAppActivity : AppCompatActivity() {
    private lateinit var fragment : Fragment

    private lateinit var _session : Session

    override fun onCreate(savedInstanceState: Bundle?) {
        _session = Session()

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_app)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frgt_mainAppActivity_buttons, MenuAppFragment.newInstance{
                if(it == 1) fragment = EventManagerFragment.newInstance()
                else if (it == 3) fragment = RequestManagerFragment.newInstance()
                else if (it == 4) fragment = AnnouncementsManagerFragment.newInstance()
                else if (it == 5) fragment = ProfilFragment.newInstance()
                else if(it == 6) {
                    _session.delete()
                    Intent(this, MainActivity::class.java).also {
                        startActivity(it)
                        this.finish()
                    }
                }

                if(it != 6){
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frgt_mainAppActivity_buttons, fragment, "EventsFragment")
                        .addToBackStack("EventsFragment")
                        .commit()
                }
            }, "AppFragment")
            .commit()
    }

    fun changeFragment(dto:DtoInputEvent){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frgt_mainAppActivity_buttons, DetailEventFragment.newInstance(dto), "detailEvent")
            .addToBackStack("detailEvent")
            .commit()
    }
}