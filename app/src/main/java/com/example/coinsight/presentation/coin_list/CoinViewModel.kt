package com.example.coinsight.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinsight.domain.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {

    // Backing property to avoid public mutation
    private val _state = MutableStateFlow(CoinListState())
    val state: StateFlow<CoinListState> = _state.asStateFlow()

    init {
        observeCoins()
        refreshCoins()
    }

    /**
     * Continuous monitoring of our Single Source of Truth (Room DB).
     * Any database modifications automatically update this stream.
     */
    private fun observeCoins() {
        repository.getCoins()
            .onEach { coinList ->
                _state.update { it.copy(coins = coinList) }
            }
            .launchIn(viewModelScope)
    }

    /**
     * Pulls down fresh data from the API and drops it into Room.
     */
    fun refreshCoins() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            val result = repository.refreshCoins()

            result.onSuccess {
                _state.update { it.copy(isLoading = false) }
            }
                .onFailure { exception ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = exception.localizedMessage ?: "An unexpected error occurred"
                        )
                    }
                }
        }
    }
}