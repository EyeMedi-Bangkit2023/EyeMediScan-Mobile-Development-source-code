package com.capstone.mybottomnav.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capstone.mybottomnav.data.Card

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: List<Card>)

    @Query("SELECT * FROM feedback")
    fun getAllQuote(): PagingSource<Int, Card>

    @Query("DELETE FROM feedback")
    suspend fun deleteAll()
}