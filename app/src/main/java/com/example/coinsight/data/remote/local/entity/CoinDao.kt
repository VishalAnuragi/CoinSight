package com.example.coinsight.data.remote.local.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

    // The Senior Touch: Notice this returns a Flow, NOT a suspend function.
    // This allows the UI to constantly observe the database for real-time updates.
    @Query("SELECT * FROM coin_table")
    fun getCoins(): Flow<List<CoinEntity>>

    // When the network fetches new data, we replace the old cached data.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(coins: List<CoinEntity>)

    @Query("DELETE FROM coin_table")
    suspend fun clearCoins()
}