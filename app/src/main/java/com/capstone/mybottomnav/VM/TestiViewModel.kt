package com.capstone.mybottomnav.VM

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.mybottomnav.Api.ApiConfig
import com.capstone.mybottomnav.response.TestiResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class TestiViewModel(application: Application): ViewModel()  {

    companion object{
        private const val TAG = "TestiViewModel"
    }

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    private val _loginResponse = MutableLiveData<TestiResponse>()
    val loginResponse: LiveData<TestiResponse> = _loginResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    fun testi(token: String, nama: String, testimoni: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().feedback(token, nama, testimoni)
        if (client != null) {
            client.enqueue(object : retrofit2.Callback<TestiResponse> {
                override fun onResponse(
                    call: Call<TestiResponse>,
                    response: Response<TestiResponse>
                ) {
                    if (response.isSuccessful) {
                        _isLoading.value = false
                        _loginResponse.value = response.body()
                        _toastMessage.value = "Feedback Added"
                    } else  {
                        _isLoading.value = false
                        try {
                            val errorBody = response.errorBody()?.string()
                            val jsonObject = JSONObject(errorBody ?: "")
                            _toastMessage.value = "Add Feedback failed: ${jsonObject.optString("message")}"
                        } catch (e: Exception) {
                            Log.e(TAG, "Exception: ${e.message}")
                        }
                    }
                }
                override fun onFailure(call: Call<TestiResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e(TAG, "onFailure : ${t.message}")
                }
            })
        }
    }

}