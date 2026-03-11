package com.plcoding.cryptocurrencyappyt.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.plcoding.cryptocurrencyappyt.domain.model.Coin

@Entity(tableName = "coins")
data class CoinEntity(
    @PrimaryKey val id: String,
    val isActive: Boolean,
    val name: String,
    @ColumnInfo(name = "coin_rank") val rank: Int,
    val symbol: String,
)

fun CoinEntity.toCoin(): Coin = Coin(
    id = id,
    isActive = isActive,
    name = name,
    rank = rank,
    symbol = symbol,
)