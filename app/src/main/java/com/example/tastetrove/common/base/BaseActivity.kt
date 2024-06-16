package com.example.tastetrove.common.base

import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected val binding : VB by lazy {
        setupViewBinding()
    }

    private var activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        setupActivityResultExt(result)
    }

    abstract fun setupViewBinding() : VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()
    }

    open fun setupViewModel() {}

    open fun setupActivityResultExt(result: ActivityResult) {}

}