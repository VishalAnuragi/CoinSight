package com.example.coinsight.data.remote.dto

import com.example.coinsight.domain.model.Coin

fun CoinDto.toDomain(): Coin {
    return Coin(
        id = this.id ?: "",
        name = this.name ?: "Unknown",
        symbol = this.symbol?.uppercase() ?: "",
        currentPrice = this.currentPrice ?: 0.0,
        priceChangePercentage24h = this.priceChangePercentage24h ?: 0.0,
        imageUrl = this.image ?: ""
    )
}