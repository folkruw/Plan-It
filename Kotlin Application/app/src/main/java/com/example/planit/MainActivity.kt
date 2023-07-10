package com.example.planit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.planit.databinding.ActivityMainBinding
import com.example.planit.fragments.HomeFragment
import com.example.planit.fragments.SignInFragment
import com.example.planit.utils.AccountManager
import com.example.planit.utils.Session

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AccountManager
    private lateinit var _session : Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PlanIt.setContext(this)
        _session = Session()

        // Check if session already connected
        if (_session.isLogged()) login()

        // Initialize viewModel
        viewModel = ViewModelProvider(this).get(AccountManager::class.java)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainerView, HomeFragment.newInstance {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, SignInFragment.newInstance {
                        viewModel.launchLoginAccount(it)
                    }, "SignInFragment")
                    .commit()
            }, "HomeFragment")
            .commit()

        viewModel.mutableLoginAccount.observe(this) {
            Toast.makeText(this, "Connecté.", Toast.LENGTH_LONG).show()
            _session.write(it)
            login()
        }
    }

    private fun login() {
        Intent(this, MainAppActivity::class.java).also {
            startActivity(it)
        }
    }

}