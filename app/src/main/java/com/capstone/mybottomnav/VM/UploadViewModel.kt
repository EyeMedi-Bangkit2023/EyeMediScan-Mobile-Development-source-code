package com.capstone.mybottomnav.VM

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.mybottomnav.Api.ApiConfig
import com.capstone.mybottomnav.response.UploadResponse
import com.capstone.mybottomnav.ui.ResultActivity
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class UploadViewModel(application: Application) : ViewModel() {

    private val _addStoriesResponse = MutableLiveData<UploadResponse>()
    val addStoriesResponse: LiveData<UploadResponse> = _addStoriesResponse
    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun uploadStory(token: String, image: MultipartBody.Part) {
        _isLoading.value = true
//        val client = ApiConfig.getApiService().uploadImage(token, image)
//        client.enqueue(object : retrofit2.Callback<UploadResponse> {
//            override fun onResponse(
//                call: Call<UploadResponse>,
//                response: Response<UploadResponse>
//            ) {
//                if (response.isSuccessful) {
//                    _isLoading.value = false
//                    _toastMessage.value = "Story Created!"
//                    if (response.body() != null) {
//                        val id = response.body()?.id
//                        _addStoriesResponse.value = response.body()
//                        _toastMessage.value = "add successful"
//                        val intentToResult = Intent(this, ResultActivity::class.java)
//                        intentToResult.putExtra(ResultActivity.ID, id)
//                        startActivity(intentToResult)
//                    }
//                } else {
//                    _isLoading.value = false
//                    try {
//                        val errorBody = response.errorBody()?.string()
//                        val jsonObject = JSONObject(errorBody ?: "")
//                        val message = jsonObject.getString("message")
//                        _toastMessage.value = "Upload failed: ${jsonObject.optString("message")}"
//
//                    } catch (e: Exception) {
//                        Log.e("Upload vm", "Exception: ${e.message}")
//                    }
//                }
//            }

//            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
//                _isLoading.value = false
//                Log.e("uploadvm", "onFailure : ${t.message}")
//            }
//
//        })
    }

}