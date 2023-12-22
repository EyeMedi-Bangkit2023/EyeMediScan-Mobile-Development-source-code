package com.capstone.mybottomnav.Api

import com.capstone.mybottomnav.response.CardResponse
import com.capstone.mybottomnav.response.FeedbackResponse
import com.capstone.mybottomnav.response.LoginResponse
import com.capstone.mybottomnav.response.RegisterResponses
import com.capstone.mybottomnav.response.TestiResponse
import com.capstone.mybottomnav.response.UploadResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @FormUrlEncoded
    @POST("api/v1/auth/register")
    fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponses>

    @FormUrlEncoded
    @POST("api/v1/auth/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @Multipart
    @POST("api/v1/predict")
    fun uploadImage(
        @Header("Authorization") token : String,
        @Part file: MultipartBody.Part
    ): Call<UploadResponse>

    @GET("api/v1/output/{id}")
    fun getData(
        @Path("id") id: Int
    ): Call<CardResponse>

    @FormUrlEncoded
    @POST("api/v1/feedback")
    fun feedback(
        @Header("Authorization") token : String,
        @Field("nama") nama: String,
        @Field("testimoni") testimoni: String
    ): Call<TestiResponse>

    @GET("api/v1/feedback")
    suspend fun getPaging(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): FeedbackResponse

}