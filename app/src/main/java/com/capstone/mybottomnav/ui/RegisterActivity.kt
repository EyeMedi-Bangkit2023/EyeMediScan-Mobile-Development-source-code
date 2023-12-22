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
import com.capstone.mybottomnav.R
import com.capstone.mybottomnav.VM.RegisterViewModel
import com.capstone.mybottomnav.VM.ViewModelFactory
import com.capstone.mybottomnav.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(),  View.OnClickListener {

    private var _registerActivityBinding: ActivityRegisterBinding? = null
    private val binding get() = _registerActivityBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _registerActivityBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupView()
        binding?.loginLinkTextView?.setOnClickListener(this)
        registerViewModel = obtainViewModel(this@RegisterActivity)
        registerViewModel.Loadingld.observe(this){
            showLoading(it)
        }
        registerViewModel.toastMessage.observe(this) { message ->
            showToast(message)
        }
        buttonRegister()
        registerViewModel.registerResponse.observe(this) { isSuccess ->
            if (isSuccess.error == false) {
                moveToLogin()
            }
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
    private fun buttonRegister() {
        binding?.buttonRegister?.setOnClickListener {
            val nama = binding?.nameEditText
            val email = binding?.emailEditText
            val password = binding?.passwordEditText

//            if(nama.error.isNullOrEmpty() || email.error.isNullOrEmpty() || password.error.isNullOrEmpty()){
//                emptyField(nama.text.toString(), email.text.toString(), password.text.toString())
//            }

            // if (nama.text?.isNotEmpty()!!  && email.text?.isNotEmpty()!! && password.text?.isNotEmpty()!! && password.text.toString().length >= 8) {
            registerViewModel.userRegister(nama?.text.toString(), email?.text.toString(), password?.text.toString())
            //  }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, OnBoardingActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }
    private fun moveToLogin() {
        val intentLogin = Intent(this, LoginActivity::class.java)
        startActivity(intentLogin)
    }
    private fun obtainViewModel(activity: AppCompatActivity): RegisterViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(RegisterViewModel::class.java)
    }
    private fun showLoading(state: Boolean) {
        binding?.progressBar?.visibility = if (state) View.VISIBLE else View.GONE
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.loginLinkTextView ->moveToLogin()
        }
    }
}