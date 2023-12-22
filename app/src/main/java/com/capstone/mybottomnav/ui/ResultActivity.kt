package com.capstone.mybottomnav.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.capstone.mybottomnav.MainActivity
import com.capstone.mybottomnav.R
import com.capstone.mybottomnav.VM.ResultViewModel
import com.capstone.mybottomnav.VM.TestiViewModel
import com.capstone.mybottomnav.VM.ViewModelFactory
import com.capstone.mybottomnav.data.UserPreferences
import com.capstone.mybottomnav.data.Uservar
import com.capstone.mybottomnav.databinding.ActivityResultBinding
import com.capstone.mybottomnav.response.CardResponse

class ResultActivity : AppCompatActivity() {
    private var _acitivityResultBinding: ActivityResultBinding? = null
    private val binding get() = _acitivityResultBinding
    private lateinit var getuser: Uservar
    private lateinit var viewModel : ResultViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _acitivityResultBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupView()

        val userPref = UserPreferences(this)
        getuser = userPref.getUser()
        TestiActivity.TOKEN = "Bearer " + getuser.token
        viewModel = obtainViewModel(this@ResultActivity)
        viewModel.toastMessage.observe(this) { message ->
            showToast(message)
        }
        viewModel.loginResponse.observe(this, { output ->
            setData(output)
        })
        val id = intent.getIntExtra(ID, 0)
        viewModel.getdata(id)
        binding?.apply {
           homeButton.setOnClickListener {
               val intent = Intent(this@ResultActivity, MainActivity::class.java)
               startActivity(intent)
           }
            testiButton.setOnClickListener {
                val intent = Intent(this@ResultActivity, TestiActivity::class.java)
                startActivity(intent)
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }
    private fun setData(output: CardResponse) {
        binding?.diagnose?.text = output.namaPenyakit
        binding?.definition?.text = output.definisi
        binding?.penyebab?.text = output.penyebab


    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
            )
        }
        supportActionBar?.hide()
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun obtainViewModel(activity: AppCompatActivity): ResultViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(ResultViewModel::class.java)
    }
    companion object {
        var TOKEN: String? = null
        var ID = "extra_id"
    }
}


