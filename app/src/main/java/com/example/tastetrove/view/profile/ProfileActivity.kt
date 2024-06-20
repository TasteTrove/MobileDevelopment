package com.example.tastetrove.view.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tastetrove.ViewModelFactory
import com.example.tastetrove.common.ext.startActivityExt
import com.example.tastetrove.databinding.ActivityProfileBinding
import com.example.tastetrove.view.main.MainActivity
import com.example.tastetrove.view.scan.ScanActivity
import com.example.tastetrove.view.search.SearchActivity
import com.example.tastetrove.view.welcome.WelcomeActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAction()
        setupViewModel()

        viewModel.getSession().observe(this) { user ->
            user?.let {
                binding.tvUser.text = user.name
                binding.tvEmail.text = user.email
            }
        }
    }

    private fun setupAction() {
        binding.actionLogout.setOnClickListener {
            viewModel.logout()
        }

        binding.btnCamera.setOnClickListener {
            startActivityExt<ScanActivity> {

            }
        }

        binding.btnProfile.setOnClickListener {
            startActivityExt<ProfileActivity> {

            }
        }

        binding.btnSearch.setOnClickListener {
            startActivityExt<SearchActivity> {

            }
        }

        binding.btnHome.setOnClickListener {
            startActivityExt<MainActivity> {

            }
        }
    }

    private fun setupViewModel() {
        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            } else {
                loadStories()
            }
        }
    }

    private fun loadStories() {
        binding.loadingProgressBar.visibility = View.GONE
    }

}
