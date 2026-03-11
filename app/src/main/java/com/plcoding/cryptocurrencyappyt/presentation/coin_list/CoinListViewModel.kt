package com.plcoding.cryptocurrencyappyt.presentation.coin_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.domain.use_case.GetCoins
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoins: GetCoins,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow<CoinListState>(CoinListState())
    val state: StateFlow<CoinListState> = _state.asStateFlow()

    init {
        loadCoins()
    }

    private fun loadCoins() {
        viewModelScope.launch {
            getCoins().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(isLoading = false, coins = result.data, error = "")
                    }
                    is Resource.Error -> {
                        println("~~~ error = ${result.message}")
                        _state.value = _state.value.copy(isLoading = false, error = result.message)
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true, error = "")
                    }
                }
            }
        }
    }

}

