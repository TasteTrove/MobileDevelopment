package com.example.tastetrove.view.login


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
import com.example.tastetrove.ViewModelFactory
import com.example.tastetrove.data.pref.UserModel
import com.example.tastetrove.databinding.ActivityLoginBinding
import com.example.tastetrove.view.main.MainActivity
import com.example.tastetrove.data.response.Result

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        login()
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


    private fun login(){
        with(binding){
            loginButton.setOnClickListener{
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                viewModel.login(email, password).observe(this@LoginActivity) {
                    when (it){
                        is Result.Loading -> {
                            showLoading(true)
                        }
                        is Result.Error ->  {
                            showLoading(false)
                            setupFail(it.error)
                            Log.e("Login Error", it.error)
                        }
                        is Result.Success -> {
                            showLoading(false)
                            it.data.loginResult.let { result ->
                                setupAction(
                                    it.data.message,
                                    result.name,
                                    result.token
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupAction(message: String, name: String, token: String) {

        val email = binding.emailEditText.text.toString()
        viewModel.saveSession(UserModel(email, name, token))

        AlertDialog.Builder(this).apply {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
            create()
            show()
        }
    }

    private fun setupFail(message: String) {
        AlertDialog.Builder(this).apply {
            setTitle("Login Gagal")
            setMessage(message)
            setPositiveButton("Lanjut") { _, _ ->
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            create()
            show()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.loadingProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}