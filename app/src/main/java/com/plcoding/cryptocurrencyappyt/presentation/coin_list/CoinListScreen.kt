package com.plcoding.cryptocurrencyappyt.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.plcoding.cryptocurrencyappyt.presentation.Screen
import com.plcoding.cryptocurrencyappyt.presentation.common.ErrorView
import com.plcoding.cryptocurrencyappyt.presentation.common.LoadingView
import com.plcoding.cryptocurrencyappyt.presentation.ui.theme.CryptocurrencyAppYTTheme

@Composable
fun CoinListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CoinListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        when {
            state.value.isLoading -> LoadingView()
            state.value.error.isNotBlank() -> ErrorView(state.value.error)
            else -> CoinList(state, navController)
        }
    }
}

@Composable
fun CoinList(state: State<CoinListState>, navController: NavController){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.value.coins, key = {it.id}) { coin ->
            CoinListItem(
                coin = coin,
                onClick = {
                    navController.navigate(Screen.CoinDetailScreen.route + "/${it.id}")
                }
            )
        }
    }
}