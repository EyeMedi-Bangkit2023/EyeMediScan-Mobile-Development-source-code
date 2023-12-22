package com.capstone.mybottomnav.response

import com.google.gson.annotations.SerializedName

data class CardResponse(

	@field:SerializedName("definisi")
	val definisi: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("nama_penyakit")
	val namaPenyakit: String? = null,

	@field:SerializedName("penyebab")
	val penyebab: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
