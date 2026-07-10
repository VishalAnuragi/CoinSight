package com.example.coinsight.data.remote.local.entity

import com.example.coinsight.domain.model.Coin


fun CoinEntity.toDomain(): Coin {
    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        currentPrice = currentPrice,
        priceChangePercentage24h = priceChangePercentage24h,
        imageUrl = imageUrl
    )
}

fun Coin.toEntity(): CoinEntity {
    return CoinEntity(
        id = id,
        name = name,
        symbol = symbol,
        currentPrice = currentPrice,
        priceChangePercentage24h = priceChangePercentage24h,
        imageUrl = imageUrl
    )
}