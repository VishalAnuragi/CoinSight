package com.example.coinsight.data.remote.local.entity

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CoinEntity::class],
    version = 1,
    exportSchema = false // We set this to false for now to avoid schema export warnings
)
abstract class CoinDatabase : RoomDatabase() {
    abstract val coinDao: CoinDao
}