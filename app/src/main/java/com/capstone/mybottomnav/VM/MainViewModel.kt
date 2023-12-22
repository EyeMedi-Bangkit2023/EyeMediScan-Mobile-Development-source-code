package com.capstone.mybottomnav.VM

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.capstone.mybottomnav.data.Card
import com.capstone.mybottomnav.db.CardRepository

class MainViewModel(private val storiesRepository: CardRepository) : ViewModel() {

    var stories: LiveData<PagingData<Card>> =
        storiesRepository.getStories(). cachedIn(viewModelScope)
}