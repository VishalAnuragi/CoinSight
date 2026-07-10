package com.example.coinsight.presentation.coin_list

import com.example.coinsight.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val errorMessage: String? = null
)