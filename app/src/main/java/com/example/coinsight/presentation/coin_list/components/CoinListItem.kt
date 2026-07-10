package com.example.coinsight.presentation.coin_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.coinsight.domain.model.Coin

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.layout.*

@Composable
fun CoinListItem(
    coin: Coin,
    onItemClick: (Coin) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(coin) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${coin.name} (${coin.symbol})",
            style = MaterialTheme.typography.bodyLarge,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "$${coin.currentPrice}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "${if(coin.priceChangePercentage24h > 0) "+" else ""}${coin.priceChangePercentage24h}%",
                color = if(coin.priceChangePercentage24h > 0) Color.Green else Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}