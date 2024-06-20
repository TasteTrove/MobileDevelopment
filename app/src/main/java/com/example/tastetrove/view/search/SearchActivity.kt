package com.example.tastetrove.view.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tastetrove.ViewModelFactory
import com.example.tastetrove.common.ext.startActivityExt
import com.example.tastetrove.data.adapter.FoodAdapter
import com.example.tastetrove.data.response.FoodsResponseItem
import com.example.tastetrove.databinding.ActivitySearchBinding
import com.example.tastetrove.view.main.MainActivity
import com.example.tastetrove.view.profile.ProfileActivity
import com.example.tastetrove.view.scan.ResultActivity
import com.example.tastetrove.view.scan.ScanActivity

class SearchActivity : AppCompatActivity() {

    private val viewModel by viewModels<SearchViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var binding : ActivitySearchBinding
    private lateinit var foodAdapter: FoodAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        foodAdapter = FoodAdapter()
        onItemClick()
        setupAction()
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    // TODO :: Benerin Ini
                    // viewModel.getStories(searchView.text.toString())
                    searchView.hide()
                    false

                }
        }
    }

        private fun onItemClick() {
            foodAdapter.setOnItemClickCallback(object : FoodAdapter.OnItemClickCallback {
                override fun onItemClicked(item: FoodsResponseItem) {
                    startActivity(
                        Intent(this@SearchActivity, ResultActivity::class.java)
                    )
                }
            })
        }

    private fun setupAction() {

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

        binding.btnHome.setOnClickListener{
            startActivityExt<MainActivity> {

            }
        }
    }
}