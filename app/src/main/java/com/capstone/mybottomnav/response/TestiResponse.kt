package com.capstone.mybottomnav.response

import com.google.gson.annotations.SerializedName

data class TestiResponse(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("testimoni")
	val testimoni: String? = null,

	@field:SerializedName("updateAt")
	val updateAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("userId")
	val userId: String? = null
)
