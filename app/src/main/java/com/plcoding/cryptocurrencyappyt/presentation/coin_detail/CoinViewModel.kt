package com.plcoding.cryptocurrencyappyt.presentation.coin_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Constants
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.use_case.GetCoin
import com.plcoding.cryptocurrencyappyt.domain.use_case.GetCoins
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getCoin: GetCoin,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow<CoinDetailState>(CoinDetailState())
    val state: StateFlow<CoinDetailState> = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            loadCoin(coinId)
        }
    }

    private fun loadCoin(coinId: String) {
        viewModelScope.launch {
            getCoin(coinId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(isLoading = false, coin = result.data, error = "")
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(isLoading = false, error = result.message)
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(coin = null, isLoading = true, error = "")
                    }
                }
            }
        }
    }

}

