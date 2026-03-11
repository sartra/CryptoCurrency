package com.plcoding.cryptocurrencyappyt.presentation.coin_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plcoding.cryptocurrencyappyt.R
import androidx.compose.ui.text.font.FontStyle
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.presentation.ui.theme.CryptocurrencyAppYTTheme


@Composable
fun CoinListItem(
    coin: Coin,
    onClick: (Coin) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable { onClick(coin) }
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .testTag("coin_list_item")
        )
        Text(
            text = if (coin.isActive) stringResource(R.string.active) else stringResource(R.string.inactive),
            color = if (coin.isActive) Color.Green else Color.Red,
            style = MaterialTheme.typography.bodySmall.copy (fontStyle = FontStyle.Italic),
            textAlign = TextAlign.End,
            modifier = Modifier.testTag("coin_list_item_status").align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
private fun CoinListItemPreview() {
    CryptocurrencyAppYTTheme {
        CoinListItem(
            coin = Coin(
                id = "1",
                isActive = true,
                name = "coin name",
                rank = 1,
                symbol = "coin symbol",
            ),
            onClick = { },
        )
    }
}