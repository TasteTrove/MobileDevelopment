package com.example.tastetrove.view.signup

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tastetrove.data.response.Result
import com.example.tastetrove.ViewModelFactory
import com.example.tastetrove.databinding.ActivitySignupBinding
import com.example.tastetrove.view.login.LoginActivity

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val viewModel by viewModels<SignupViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        signup()
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


    private fun signup(){
        with(binding){
            registerButton.setOnClickListener{
                val name = nameTextView.text.toString()
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                viewModel.register(email, password, name).observe(this@SignupActivity) { result ->
                    when (result){
                        is Result.Loading -> {
                            showLoading(true)
                        }

                        is Result.Success -> {
                            showLoading(false)
                            setupAction(result.data.message)
                            Log.d("Signup Berhasil", result.data.message)
                        }
                        is Result.Error ->  {
                            showLoading(false)
                            setupFail(result.error)
                            Log.e("Signup Error", result.error)
                        }
                    }
                }
            }
        }
    }

    private fun setupFail(message: String) {
        AlertDialog.Builder(this).apply {
            setTitle("Signup Gagal")
            setMessage(message)
            setPositiveButton("Lanjut") { _, _ ->
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            create()
            show()
        }
    }

    private fun setupAction(message: String) {
        binding.registerButton.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("Yeah!")
                setMessage(message)
                setPositiveButton("Lanjut") { _, _ ->
                    startActivity(Intent(context, LoginActivity::class.java))
                    finish()
                }
                create()
                show()
            }
        }
    }
    private fun showLoading(isLoading: Boolean) {
        binding.loadingProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}