package com.capstone.mybottomnav.ui

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.capstone.mybottomnav.MainActivity
import com.capstone.mybottomnav.R
import com.capstone.mybottomnav.VM.LoginViewModel
import com.capstone.mybottomnav.VM.TestiViewModel
import com.capstone.mybottomnav.VM.ViewModelFactory
import com.capstone.mybottomnav.data.UserPreferences
import com.capstone.mybottomnav.data.Uservar
import com.capstone.mybottomnav.databinding.ActivityTestiBinding
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class TestiActivity : AppCompatActivity() {

    private var _activityAddStorybinding: ActivityTestiBinding? = null
    private val binding get() = _activityAddStorybinding
    private lateinit var viewModel : TestiViewModel
    private lateinit var getuser: Uservar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityAddStorybinding = ActivityTestiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val userPref = UserPreferences(this)
        getuser = userPref.getUser()
        TOKEN = "Bearer " + getuser.token
        upload(TOKEN.toString())

        viewModel = obtainViewModel(this@TestiActivity)

        viewModel.toastMessage.observe(this) { message ->
            showToast(message)
        }
        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
//        viewModel.loginResponse.observe(this) { response ->
//            if (response.error == false) {
//                val intent = Intent(this@TestiActivity, MainActivity::class.java)
//                startActivity(intent)
//            }
//        }
        binding?.btnBack?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            finish()
        }
    }

    private fun upload(tokenauth: String) {
        binding?.loginButton?.setOnClickListener {
            val description =
                binding?.testiEditText?.text.toString()
            val name = binding?.emailEditText?.text.toString()
            TOKEN?.let {
                viewModel.testi(
                    it,
                    name,
                    description
                )
            }
            val intent = Intent(this@TestiActivity, MainActivity::class.java)
            startActivity(intent)

        }
    }

    companion object {
        var TOKEN: String? = null
        const val CAMERA_X_RESULT = 200
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
//    private fun showLoading(state: Boolean) {
//        binding?.progressBar?.visibility = if (state) View.VISIBLE else View.GONE
//    }
    private fun showLoading(state: Boolean) {
        binding?.progressBar?.visibility = if (state) View.VISIBLE else View.GONE
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun obtainViewModel(activity: AppCompatActivity): TestiViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(TestiViewModel::class.java)
    }
}