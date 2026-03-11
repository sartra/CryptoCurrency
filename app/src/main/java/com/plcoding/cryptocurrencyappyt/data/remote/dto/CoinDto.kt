package com.plcoding.cryptocurrencyappyt.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.plcoding.cryptocurrencyappyt.data.local.entity.CoinEntity
import com.plcoding.cryptocurrencyappyt.domain.model.Coin

data class CoinDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    @SerializedName("rank")
    val rank: Int?,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoinEntity(): CoinEntity = CoinEntity(
    id = id,
    isActive = isActive,
    name = name,
    rank = rank ?: 0,
    symbol = symbol,
)

fun CoinDto.toCoin(): Coin =
    Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank ?: 0,
        symbol = symbol,
    )