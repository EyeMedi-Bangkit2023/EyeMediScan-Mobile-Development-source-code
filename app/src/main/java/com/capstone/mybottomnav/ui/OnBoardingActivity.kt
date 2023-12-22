package com.capstone.mybottomnav.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.capstone.mybottomnav.R
import com.capstone.mybottomnav.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity(), View.OnClickListener {

    private var _welcomeActivityBinding: ActivityOnBoardingBinding? = null
    private val binding get() = _welcomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _welcomeActivityBinding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupView()
        binding?.loginButton?.setOnClickListener(this)
        binding?.signup?.setOnClickListener(this)
    }

    private fun moveToLogin() {
        val intentLogin = Intent(this, LoginActivity::class.java)
        startActivity(intentLogin)
    }

    private fun moveToRegister() {
        val intentRegister = Intent(this, RegisterActivity::class.java)
        startActivity(intentRegister)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.loginButton ->moveToLogin()
            R.id.signup -> moveToRegister()
        }
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

}