package com.example.tastetrove.view.profile

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.tastetrove.R
import com.example.tastetrove.databinding.ActivityProfileBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProfileActivity : AppCompatActivity() {


    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getStringExtra("user")

        if (user != null) {
            val profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
            profileViewModel.isLoading.observe(this) {
                showLoading(it)
            }
            profileViewModel.getUser(this, user)
            profileViewModel.user.observe(this) { user ->
                user?.let {
                    binding.tvUser.text = it.userResponse.nama ?: ""
                    binding.tvEmail.text = it.userResponse.nama ?: ""

                }
            }

        }
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingProgressBar.visibility = View.VISIBLE
        } else {
            binding.loadingProgressBar.visibility = View.GONE
        }
    }

}
