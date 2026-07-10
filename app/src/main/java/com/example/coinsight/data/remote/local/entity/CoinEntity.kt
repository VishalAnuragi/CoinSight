package com.example.coinsight.data.remote.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_table")
data class CoinEntity(
    @PrimaryKey val id: String,
    val name: String,
    val symbol: String,
    val currentPrice: Double,
    val priceChangePercentage24h: Double,
    val imageUrl: String
)