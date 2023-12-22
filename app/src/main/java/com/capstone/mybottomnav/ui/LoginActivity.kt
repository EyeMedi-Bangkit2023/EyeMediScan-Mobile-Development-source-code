package com.capstone.mybottomnav.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.capstone.mybottomnav.MainActivity
import com.capstone.mybottomnav.R
import com.capstone.mybottomnav.VM.LoginViewModel
import com.capstone.mybottomnav.VM.ViewModelFactory
import com.capstone.mybottomnav.data.UserPreferences
import com.capstone.mybottomnav.data.Uservar
import com.capstone.mybottomnav.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener  {
    private var _loginActivityBinding: ActivityLoginBinding? = null
    private val binding get() = _loginActivityBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var uservar: Uservar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _loginActivityBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupView()
        binding?.loginLinkTextView?.setOnClickListener(this)
        loginViewModel = obtainViewModel(this@LoginActivity)
        setupView()
        buttonLogin()
        showLoading(false)
        setuserlogin()
        loginViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        loginViewModel.toastMessage.observe(this) { message ->
            showToast(message)
        }
    }
    private fun emptyField(email: String, password: String){
        if(email.isEmpty()){
            binding?.emailEditText?.error = "Email tidak boleh kosong"
        }
        if(password.isEmpty()){
            binding?.passwordEditText?.error = "Password tidak boleh kosong"
        }
    }
    private fun buttonLogin() {
        binding?.loginButton?.setOnClickListener {
            val email = binding?.emailEditText
            val password = binding?.passwordEditText

            if (email?.error.isNullOrEmpty() || password?.error.isNullOrEmpty()) {
                emptyField(email?.text.toString(), password?.text.toString())
            }

            if (email?.text?.isNotEmpty()!! && password?.text?.isNotEmpty()!!) {
            loginViewModel.userLogin(email?.text.toString(), password?.text.toString() )
           }
        }
    }
    private fun setuserlogin(){
        loginViewModel.loginResponse.observe(this){ response ->
            if (response.error == false) {
                val userName = response.loginResult?.name.toString()
                val userId = response.loginResult?.userId.toString()
                val userToken = response.loginResult?.token.toString()
                val Pref = UserPreferences(this)
                val userData = Uservar()

                userData.name = userName
                userData.id = userId
                userData.token = userToken
                uservar = userData

                Pref.setUser(uservar)
                val intentToHome = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intentToHome)
            }
        }
    }
    private fun showLoading(state: Boolean) {
        binding?.progressBar?.visibility = if (state) View.VISIBLE else View.GONE
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun obtainViewModel(activity: AppCompatActivity): LoginViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(LoginViewModel::class.java)
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
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, OnBoardingActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }

    private fun moveToRegister() {
        val intentRegister = Intent(this, RegisterActivity::class.java)
        startActivity(intentRegister)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.loginLinkTextView -> moveToRegister()
        }
    }

}