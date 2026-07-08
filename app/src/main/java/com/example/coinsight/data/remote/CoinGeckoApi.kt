package com.example.coinsight.data.remote

import com.example.coinsight.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoApi {

    // The endpoint to fetch market data for a list of coins
    @GET("api/v3/coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") currency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 50,
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = false
    ): List<CoinDto>

    companion object {
        const val BASE_URL = "https://api.coingecko.com/"
    }
}