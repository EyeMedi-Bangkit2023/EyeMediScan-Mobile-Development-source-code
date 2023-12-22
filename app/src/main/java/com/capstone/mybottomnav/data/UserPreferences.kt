package com.capstone.mybottomnav.data

import android.content.Context
import android.content.SharedPreferences

internal class UserPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getUser(): Uservar {
        val name = sharedPreferences.getString(NAME, "") ?: ""
        val id = sharedPreferences.getString(ID, "") ?: ""
        val token = sharedPreferences.getString(TOKEN, "") ?: ""
        return Uservar(name, id, token)
    }

    fun setUser(value: Uservar) {
        val editor = sharedPreferences.edit()
        editor.putString(NAME, value.name)
        editor.putString(ID, value.id)
        editor.putString(TOKEN, value.token)
        editor.apply()
    }

    fun setLogout() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private const val NAME = "name"
        private const val ID = "id"
        private const val TOKEN = "token"
        private const val PREFS_NAME = "user_preferences"
    }
}