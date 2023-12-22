package com.capstone.mybottomnav.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.capstone.mybottomnav.Api.ApiConfig
import com.capstone.mybottomnav.MainActivity
import com.capstone.mybottomnav.R
import com.capstone.mybottomnav.VM.UploadViewModel
import com.capstone.mybottomnav.VM.ViewModelFactory
import com.capstone.mybottomnav.data.UserPreferences
import com.capstone.mybottomnav.data.Uservar
import com.capstone.mybottomnav.databinding.ActivityUploadBinding
import com.capstone.mybottomnav.response.UploadResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.File

class UploadActivity : AppCompatActivity() {

    private var _activityAddStorybinding: ActivityUploadBinding? = null
    private val binding get() = _activityAddStorybinding
    private lateinit var viewModel : UploadViewModel
    private var currentImageUri: Uri? = null
    private var getFoto: File? = null
    private lateinit var getuser: Uservar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityAddStorybinding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val userPref = UserPreferences(this)
        getuser = userPref.getUser()
        TOKEN = "Bearer " + getuser.token
        viewModel = obtainViewModel(this@UploadActivity)
        viewModel.isLoading.observe(this){
            showLoading(it)
        }
        viewModel.toastMessage.observe(this) { message ->
            showToast(message)
        }

        viewModel.addStoriesResponse.observe(this) { response ->
            if (response.error == false) {
                val intent = Intent(this@UploadActivity, ResultActivity::class.java)
                startActivity(intent)
            }
        }
        binding?.apply {
            binding?.btnGallery?.setOnClickListener { startGallery() }
            binding?.btnUpload?.setOnClickListener { uploadImage(TOKEN.toString()) }
        }
    }
    companion object {
        var TOKEN: String? = null
        const val CAMERA_X_RESULT = 200
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding?.progressBar?.visibility = View.VISIBLE
        } else {
            binding?.progressBar?.visibility = View.INVISIBLE
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun obtainViewModel(activity: AppCompatActivity): UploadViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(UploadViewModel::class.java)
    }
    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val pilih = Intent.createChooser(intent, "Pilih Foto!")
        launcherGallery.launch(pilih)
    }
    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                val foto = uriToFile(uri, this@UploadActivity)
                getFoto = foto
                binding?.postImage?.setImageURI(uri)
                if(binding?.postImage?.drawable != null){
                    binding?.takePictureText?.visibility = View.GONE
                }
            }
        }
    }

    private fun uploadImage(tokenauth: String) {
        showLoading(true)
        if (getFoto != null) {
            val file = reduceFileImage(getFoto as File)
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData("file", file.name, requestImageFile
            )
//            TOKEN?.let {
//                viewModel.uploadStory(
//                    it,
//                    imageMultipart
//                )
//            }
            val client = TOKEN?.let { ApiConfig.getApiService().uploadImage(it, imageMultipart) }
            client?.enqueue(object : retrofit2.Callback<UploadResponse> {

                override fun onResponse(
                    call: Call<UploadResponse>,
                    response: Response<UploadResponse>
                ) {
                    showLoading(false)
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val id = response.body()?.id
                            val intentToResult = Intent(this@UploadActivity, ResultActivity::class.java)
                            intentToResult.putExtra(ResultActivity.ID, id)
                            startActivity(intentToResult)
                        }
                    } else {
                        Toast.makeText(this@UploadActivity, response.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                    Log.d("UploadActivity", "OnFailure : ${t.message}")
                    Toast.makeText(this@UploadActivity, "Gagal melakukan upload gambar", Toast.LENGTH_SHORT).show()
                }

            })
        } else {
            Toast.makeText(this, "Silahkan masukkan berkas gambar terlebih dahulu", Toast.LENGTH_SHORT).show()
        }
    }


    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "no permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }
}