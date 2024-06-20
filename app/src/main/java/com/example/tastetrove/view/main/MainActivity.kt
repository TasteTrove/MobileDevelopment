package com.example.tastetrove.view.main


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tastetrove.ViewModelFactory
import com.example.tastetrove.common.ext.startActivityExt
import com.example.tastetrove.data.adapter.FoodAdapter
import com.example.tastetrove.data.response.Result
import com.example.tastetrove.databinding.ActivityMainBinding
import com.example.tastetrove.view.favorite.FavoriteActivity
import com.example.tastetrove.view.profile.ProfileActivity
import com.example.tastetrove.view.scan.ScanActivity
import com.example.tastetrove.view.search.SearchActivity
import com.example.tastetrove.view.welcome.WelcomeActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var foodAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupRecyclerView()
        setupViewModel()
        setupAction()
        viewModel.getFoods().observe(this){
            when(it){
                is Result.Error -> {}
                Result.Loading -> {}
                is Result.Success -> {
                    foodAdapter.submitList(it.data)
                }
            }
        }
    }


    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupRecyclerView() {
        foodAdapter = FoodAdapter()
        binding.rvFood.layoutManager = LinearLayoutManager(this)
        binding.rvFood.adapter = foodAdapter
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

    private fun setupAction() {
        binding.actionFav.setOnClickListener {
            startActivityExt<FavoriteActivity> {

            }
        }

        binding.btnCamera.setOnClickListener {
            startActivityExt<ScanActivity> {

            }
        }

        binding.btnProfile.setOnClickListener{
            startActivityExt<ProfileActivity> {

            }
        }

        binding.btnSearch.setOnClickListener{
            startActivityExt<SearchActivity> {

            }
        }
    }

    private fun loadStories() {
        binding.loadingProgressBar.visibility = View.GONE
        lifecycleScope.launch {
            viewModel.getFoods()
        }
    }
}