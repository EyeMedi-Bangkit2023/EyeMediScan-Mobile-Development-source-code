package com.capstone.mybottomnav.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Uservar(
    var name: String? = null,
    var id: String? = null,
    var token: String? = null
) : Parcelable