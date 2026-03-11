package com.plcoding.cryptocurrencyappyt.common

object Constants {

    const val BASE_URL = "https://api.coinpaprika.com/"

    const val PARAM_COIN_ID = "coinId"

    /** Maximum coins to load from Room; avoids CursorWindow overflow (~2MB limit) with 7000+ rows */
    const val COIN_LIST_LIMIT = 500
}