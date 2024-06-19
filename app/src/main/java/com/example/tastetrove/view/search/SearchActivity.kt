package com.example.tastetrove.view.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tastetrove.ViewModelFactory
import com.example.tastetrove.data.adapter.FoodAdapter
import com.example.tastetrove.data.response.ListStoryItem
import com.example.tastetrove.databinding.ActivitySearchBinding
import com.example.tastetrove.view.detail.DetailActivity
import com.example.tastetrove.view.login.LoginViewModel

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
                override fun onItemClicked(item: ListStoryItem) {
                    startActivity(
                        Intent(this@SearchActivity, DetailActivity::class.java)
                    )
                }
            })
        }
}