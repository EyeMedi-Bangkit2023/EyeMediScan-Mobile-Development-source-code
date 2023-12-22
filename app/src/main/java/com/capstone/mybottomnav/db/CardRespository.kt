package com.capstone.mybottomnav.db

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.capstone.mybottomnav.Api.ApiService
import com.capstone.mybottomnav.data.Card

class CardRepository(private val storiesDatabase: RoomDb,
                      private val apiService: ApiService
) {
    fun getStories(): LiveData<PagingData<Card>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),

            remoteMediator = RemoteMediator(storiesDatabase, apiService),
            pagingSourceFactory = {
                storiesDatabase.storyDao().getAllQuote()
            }
        ).liveData

    }
}