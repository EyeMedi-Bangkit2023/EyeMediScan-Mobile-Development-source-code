package com.capstone.mybottomnav.VM

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.mybottomnav.Api.ApiConfig
import com.capstone.mybottomnav.response.LoginResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class LoginViewModel(application: Application): ViewModel()  {
    companion object{
        private const val TAG = "LoginViewModel"
    }

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun userLogin(email: String, password: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().login(email, password)
      //  if (client != null) {
            client.enqueue(object : retrofit2.Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful && response.body()?.message == "success") {
                        _isLoading.value = false
                        _toastMessage.value = "Login successful"
                        _loginResponse.value = response.body()
                    } else  {
                        _isLoading.value = false

                        try {
                            val errorBody = response.errorBody()?.string()
                            val jsonObject = JSONObject(errorBody ?: "")
                            _toastMessage.value = "Login failed: ${jsonObject.optString("msg")}"
                        } catch (e: Exception) {
                            _toastMessage.value = "Login failed"
                            Log.e(TAG, "Exception: ${e.message}")
                        }
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e(TAG, "onFailure : ${t.message}")
                }
            })
      //  }
    }
//    fun login(id: String, token: String) {
//        viewModelScope.launch {
//            Preferencesnew.login(id,token)
//        }
//    }
}