package com.capstone.mybottomnav.VM

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.mybottomnav.Api.ApiConfig
import com.capstone.mybottomnav.response.CardResponse
import com.capstone.mybottomnav.response.TestiResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class ResultViewModel(application: Application): ViewModel() {
    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    private val _loginResponse = MutableLiveData<CardResponse>()
    val loginResponse: LiveData<CardResponse> = _loginResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    companion object {
        private const val TAG = "ResultViewModel"
    }
    fun getdata(id: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getData(id)
        if (client != null) {
            client.enqueue(object : retrofit2.Callback<CardResponse> {
                override fun onResponse(
                    call: Call<CardResponse>,
                    response: Response<CardResponse>
                ) {
                    if (response.isSuccessful) {
                        _isLoading.value = false
                        _loginResponse.value = response.body()
                        _toastMessage.value = "Scan successful"
                    } else  {
                        _isLoading.value = false
                        try {
                            val errorBody = response.errorBody()?.string()
                            val jsonObject = JSONObject(errorBody ?: "")
                            _toastMessage.value = "Scan failed: ${jsonObject.optString("message")}"
                        } catch (e: Exception) {
                            Log.e(ResultViewModel.TAG, "Exception: ${e.message}")
                        }
                    }
                }
                override fun onFailure(call: Call<CardResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e(ResultViewModel.TAG, "onFailure : ${t.message}")
                }
            })
        }
    }

}