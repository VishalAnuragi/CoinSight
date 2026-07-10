package com.example.coinsight.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.example.coinsight.presentation.coin_list.CoinListScreen
import com.example.coinsight.presentation.theme.CoinSightTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material3.Surface

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinSightTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CoinListScreen()
                }
            }
        }
    }
}