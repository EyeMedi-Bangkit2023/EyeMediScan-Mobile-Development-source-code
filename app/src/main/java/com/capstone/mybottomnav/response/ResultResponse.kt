package com.capstone.mybottomnav.response

import com.google.gson.annotations.SerializedName


data class ResultResponse(

	@field:SerializedName("definisi")
	val definisi: String? = null,

	@field:SerializedName("nama_penyakit")
	val namaPenyakit: String? = null,

	@field:SerializedName("penyebab")
	val penyebab: String? = null
)
