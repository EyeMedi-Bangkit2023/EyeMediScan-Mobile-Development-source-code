package com.capstone.mybottomnav.VM

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.mybottomnav.Api.ApiConfig
import com.capstone.mybottomnav.response.RegisterResponses
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class RegisterViewModel(application: Application) : ViewModel() {

    companion object {
        private const val TAG = "RegisterViewModel"
    }

    private val _registerResponse = MutableLiveData<RegisterResponses>()
    val registerResponse: LiveData<RegisterResponses> = _registerResponse

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    private val showLoading = MutableLiveData<Boolean>()

    val Loadingld: LiveData<Boolean> = showLoading

    fun userRegister(name: String, email: String, password: String) {
        showLoading.value = true
        val client = ApiConfig.getApiService().register(name, email, password)
        client.enqueue(object : retrofit2.Callback<RegisterResponses> {
            override fun onResponse(
                call: Call<RegisterResponses>,
                response: Response<RegisterResponses>
            ) {
                if (response.isSuccessful) {
                    showLoading.value = false
                    _registerResponse.value = response.body()
                    _toastMessage.value = "Register successful"
                } else {
                    showLoading.value = false
                    try {
                        val errorBody = response.errorBody()?.string()
                        val jsonObject = JSONObject(errorBody ?: "")
                        _toastMessage.value = "Register failed: ${jsonObject.optString("msg")}"
                    } catch (e: Exception) {
                        Log.e(TAG, "Exception: ${e.message}")
                    }
                }
            }

            override fun onFailure(call: Call<RegisterResponses>, t: Throwable) {
                showLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

    }

}