package com.example.tastetrove.view.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tastetrove.data.adapter.FoodAdapter
import com.example.tastetrove.data.response.ListStoryItem
import com.example.tastetrove.databinding.ActivitySearchBinding
import com.example.tastetrove.view.detail.DetailActivity

class SearchActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchBinding
    private lateinit var foodAdapter: FoodAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        foodAdapter = FoodAdapter()
        onItemClick()
        val mainViewModel = ViewModelProvider(this)[SearchViewModel::class.java]


        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    mainViewModel.getStories(searchView.text.toString())
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