package com.example.tastetrove.view.main


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.SearchEvent
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tastetrove.R
import com.example.tastetrove.ViewModelFactory
import com.example.tastetrove.common.ext.startActivityExt
import com.example.tastetrove.data.adapter.FoodAdapter
import com.example.tastetrove.data.pref.Food
import com.example.tastetrove.databinding.ActivityMainBinding
import com.example.tastetrove.view.favorite.FavoriteActivity
import com.example.tastetrove.view.login.LoginActivity
import com.example.tastetrove.view.profile.ProfileActivity
import com.example.tastetrove.view.scan.ScanActivity
import com.example.tastetrove.view.welcome.WelcomeActivity
import kotlinx.coroutines.launch




class MainActivity : AppCompatActivity() {

        private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var rvFood: RecyclerView
    private val list = ArrayList<Food>()

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        rvFood = findViewById(R.id.rvFood)
        rvFood.setHasFixedSize(true)

        list.addAll(getListLandmark())
        showRecyclerList()
        loadStories()
        setupViewModel()
        setupAction()
    }


    private fun getListLandmark(): ArrayList<Food> {
        val dataName = resources.getStringArray(R.array.data_food)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listFood = ArrayList<Food>()
        for (i in dataName.indices) {
            val food = Food(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listFood.add(food)
        }
        return listFood
    }

    private fun showRecyclerList() {
        rvFood.layoutManager = LinearLayoutManager(this)
        val listLandMarkAdapter = FoodAdapter(list)
        rvFood.adapter = listLandMarkAdapter
    }

    private fun setupAction() {
        binding.actionLogout.setOnClickListener {
            viewModel.logout()
        }

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

//        binding.btnSearch.setOnClickListener{
//            startActivityExt<SearchActivity> {
//
//            }
//        }
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

        override fun onResume() {
        super.onResume()
        viewModel.getStories()
    }


    private fun loadStories() {
        binding.loadingProgressBar.visibility = View.GONE
        lifecycleScope.launch {
            viewModel.getStories()
        }
    }


}









// TODO CLASS MainActivity YANG BENER
//class MainActivity : AppCompatActivity() {
//    private val viewModel by viewModels<MainViewModel> {
//        ViewModelFactory.getInstance(this)
//    }
//    private lateinit var binding: ActivityMainBinding
//    private lateinit var storyAdapter: FoodAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//        setupView()
//        setupRecyclerView()
//        setupViewModel()
//        setupAction()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        viewModel.getStories()
//    }
//
//    private fun setupView() {
//        @Suppress("DEPRECATION")
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            window.insetsController?.hide(WindowInsets.Type.statusBars())
//        } else {
//            window.setFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN
//            )
//        }
//        supportActionBar?.hide()
//    }
//
//    private fun setupRecyclerView() {
//        storyAdapter = FoodAdapter()
//        binding.rvStory.layoutManager = LinearLayoutManager(this)
//        binding.rvStory.adapter = storyAdapter
//    }
//
//    private fun setupViewModel() {
//        viewModel.getSession().observe(this) { user ->
//            if (!user.isLogin) {
//                startActivity(Intent(this, WelcomeActivity::class.java))
//                finish()
//            } else {
//                loadStories()
//            }
//        }
//
//        viewModel.foods.observe(this) { foods ->
//            Log.d("MainActivity", "Updating adapter with stories: $foods")
//            storyAdapter.submitList(foods)
//            binding.loadingProgressBar.visibility = View.GONE
//        }
//    }
//
//    private fun setupAction() {
//        binding.actionLogout.setOnClickListener {
//            viewModel.logout()
//        }
//
//        binding.actionFav.setOnClickListener {
//            startActivityExt<FavoriteActivity> {
//
//            }
//        }
//
//        binding.btnCamera.setOnClickListener {
//            startActivityExt<ScanActivity> {
//
//            }
//        }
//
//        binding.btnProfile.setOnClickListener{
//            startActivityExt<ProfileActivity> {
//
//            }
//        }
//
//        binding.btnSearch.setOnClickListener{
//            startActivityExt<SearchActivity> {
//
//            }
//        }
//    }
//
//    private fun loadStories() {
//        binding.loadingProgressBar.visibility = View.VISIBLE
//        lifecycleScope.launch {
//            viewModel.getStories()
//        }
//    }
//}