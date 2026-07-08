package com.example.coinsight.domain.repository

import com.example.coinsight.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    /**
     * Observes the local database continuously.
     * Whenever the DB updates, this Flow emits the new list to the UI.
     */
    fun getCoins(): Flow<List<Coin>>

    /**
     * Triggers a network call to fetch the latest prices and saves them to the DB.
     * We use Kotlin's built-in Result class to safely pass success/failure states.
     */
    suspend fun refreshCoins(): Result<Unit>
}