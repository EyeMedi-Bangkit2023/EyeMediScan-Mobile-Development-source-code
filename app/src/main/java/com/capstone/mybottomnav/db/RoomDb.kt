package com.capstone.mybottomnav.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.capstone.mybottomnav.data.Card

@Database(
    entities = [Card::class, RemoteKeys::class],
    version = 2,
    exportSchema = false
)
abstract class RoomDb : RoomDatabase() {

    abstract fun storyDao(): CardDao
    abstract fun remoteKeysDao(): RkDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDb? = null
        @JvmStatic
        fun getDatabase(context: Context): RoomDb {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    RoomDb::class.java, "quote_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}