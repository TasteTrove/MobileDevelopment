package com.example.tastetrove.view.scan.historyscan

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tastetrove.common.base.BaseActivity
import com.example.tastetrove.common.ext.showToast
import com.example.tastetrove.data.model.HistoryModel
import com.example.tastetrove.data.network.Resource
import com.example.tastetrove.databinding.ActivityHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity : BaseActivity<ActivityHistoryBinding>() {

    private val viewModel: HistoryViewModel by viewModels()

    override fun setupViewBinding(): ActivityHistoryBinding {
        return ActivityHistoryBinding.inflate(layoutInflater)
    }

    private val rvAdapter: HistoryViewAdapter by lazy {
        HistoryViewAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getHistory()

        binding.apply {
            rvAdapter.setListener(object : HistoryHolderOnClickListener {
                override fun onClickListener(data: HistoryModel) {

                }
            })

            rv.apply {
                adapter = rvAdapter
                layoutManager = LinearLayoutManager(
                    this@HistoryActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }
        }

    }

    override fun setupViewModel() {
        super.setupViewModel()
        viewModel.dbState.observe(this) {
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