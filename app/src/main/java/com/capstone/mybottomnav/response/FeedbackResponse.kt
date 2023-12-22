package com.capstone.mybottomnav.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class FeedbackResponse(

    @field:SerializedName("listFeedback")
    val listFeedback: List<ListFeedbackItem>,

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)
@Parcelize
data class ListFeedbackItem(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("nama")
    val nama: String? = null,

    @field:SerializedName("testimoni")
    val testimoni: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("updateAt")
    val updateAt: String? = null

): Parcelable