package com.example.tastetrove.view.favorite

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tastetrove.common.base.BaseActivity
import com.example.tastetrove.common.ext.showToast
import com.example.tastetrove.data.model.FavoriteFoodModel
import com.example.tastetrove.data.model.HistoryModel
import com.example.tastetrove.data.network.Resource
import com.example.tastetrove.databinding.ActivityFavoriteBinding
import com.example.tastetrove.databinding.ActivityHistoryBinding
import com.example.tastetrove.view.scan.historyscan.HistoryHolderOnClickListener
import com.example.tastetrove.view.scan.historyscan.HistoryViewAdapter
import com.example.tastetrove.view.scan.historyscan.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : BaseActivity<ActivityFavoriteBinding>() {

    private val viewModel: FavoriteViewModel by viewModels()

    override fun setupViewBinding(): ActivityFavoriteBinding {
        return ActivityFavoriteBinding.inflate(layoutInflater)
    }

    private val rvAdapter: FavoriteViewAdapter by lazy {
        FavoriteViewAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getData()

        binding.apply {
            rvAdapter.setListener(object : FavoriteHolderOnClickListener {
                override fun onClickListener(data: FavoriteFoodModel) {

                }
            })

            rv.apply {
                adapter = rvAdapter
                layoutManager = LinearLayoutManager(
                    this@FavoriteActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }
        }

    }

    override fun setupViewModel() {
        super.setupViewModel()
        viewModel.getDataState.observe(this) {
            when (it) {
                is Resource.Error -> {
                    showToast("Error Menampilkan Data History")
                }

                is Resource.Loading -> {}
                is Resource.Success -> {
                    rvAdapter.setItems(it.data ?: listOf())
                }
            }
        }
    }

}