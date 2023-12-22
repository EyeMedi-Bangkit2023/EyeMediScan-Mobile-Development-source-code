package com.capstone.mybottomnav

import android.content.Context
import com.capstone.mybottomnav.Api.ApiConfig
import com.capstone.mybottomnav.db.CardRepository
import com.capstone.mybottomnav.db.RoomDb

object Injection {
    fun provideRepository(context: Context): CardRepository {
        val database = RoomDb.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return CardRepository(database, apiService)
    }
}