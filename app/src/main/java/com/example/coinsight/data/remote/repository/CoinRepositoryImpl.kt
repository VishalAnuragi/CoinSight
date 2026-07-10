package com.example.coinsight.data.remote.repository

import com.example.coinsight.data.remote.CoinGeckoApi
import com.example.coinsight.data.remote.dto.toDomain
import com.example.coinsight.data.remote.local.entity.CoinDao
import com.example.coinsight.data.remote.local.entity.toDomain
import com.example.coinsight.data.remote.local.entity.toEntity
import com.example.coinsight.domain.model.Coin
import com.example.coinsight.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException

class CoinRepositoryImpl(
    private val api: CoinGeckoApi,
    private val dao: CoinDao
) : CoinRepository {

    // 1. We observe the local database.
    // We map the Flow<List<CoinEntity>> to Flow<List<Coin>> so the UI only sees pure Domain models.
    override fun getCoins(): Flow<List<Coin>> {
        return dao.getCoins().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    // 2. We fetch from the network, map to Entities, and cache in the local database.
    override suspend fun refreshCoins(): Result<Unit> {
        return try {
            val remoteCoins = api.getCoins()

            // Convert DTOs -> Domain Models -> Room Entities
            val entities = remoteCoins.map { it.toDomain().toEntity() }

            // Overwrite the local cache
            dao.insertCoins(entities)

            Result.success(Unit)
        } catch (e: HttpException) {
            // Catches non-2xx HTTP responses (e.g., 404, 500)
            Result.failure(e)
        } catch (e: IOException) {
            // Catches network drops or lack of internet connection
            Result.failure(e)
        }
    }
}