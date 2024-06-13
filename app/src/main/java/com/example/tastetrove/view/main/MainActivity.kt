package com.example.tastetrove.view.main


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tastetrove.R
import com.example.tastetrove.ViewModelFactory
import com.example.tastetrove.adapter.FoodAdapter
import com.example.tastetrove.data.adapter.MainAdapter
import com.example.tastetrove.databinding.ActivityMainBinding
import com.example.tastetrove.view.login.LoginActivity
import com.example.tastetrove.view.welcome.WelcomeActivity
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var storyAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupRecyclerView()
        setupViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFoods()
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
        storyAdapter = FoodAdapter()
        binding.rvFood.layoutManager = LinearLayoutManager(this)
        binding.rvFood.adapter = storyAdapter
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

        viewModel.stories.observe(this) { stories ->
            Log.d("MainActivity", "Updating adapter with stories: $stories")
            storyAdapter.submitList(stories)
            binding.loadingProgressBar.visibility = View.GONE
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMenu(item.itemId)
        return super.onOptionsItemSelected(item)
    }
    private fun setMenu(itemId: Int) {
        when (itemId) {
            R.id.action_logout -> {
                val builder = AlertDialog.Builder(this)
                val alert = builder.create()
                builder
                    .setTitle(getString(R.string.logout))
                    .setMessage(getString(R.string.alertMassageLogout))
                    .setPositiveButton(getString(R.string.yesLogout)) { _, _ ->
                        viewModel.logout()
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    }
                    .setNegativeButton(getString(R.string.cancelLogout)) { _, _ ->
                        alert.cancel()
                    }
                    .show()
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