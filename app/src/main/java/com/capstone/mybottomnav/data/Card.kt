package com.capstone.mybottomnav.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "feedback")
data class Card(
    @field:SerializedName("nama") var usn: String,
    @field:SerializedName("testimoni") var testi: String,
    @field:SerializedName("date") var date: String,
    @PrimaryKey @field:SerializedName("id") var id: Int
)