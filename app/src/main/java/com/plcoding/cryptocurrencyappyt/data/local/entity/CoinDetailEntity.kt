package com.plcoding.cryptocurrencyappyt.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.plcoding.cryptocurrencyappyt.data.remote.dto.TeamMember
import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail

@Entity(tableName = "coin_details")
data class CoinDetailEntity(
    @PrimaryKey val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tagsJson: String,
    val teamJson: String
)

fun CoinDetailEntity.toCoinDetail(): CoinDetail {
    val gson = Gson()
    val tags: List<String> = gson.fromJson(tagsJson, object : TypeToken<List<String>>() {}.type)
    val team: List<TeamMember> = gson.fromJson(teamJson, object : TypeToken<List<TeamMember>>() {}.type)
    return CoinDetail(
        coinId = coinId,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        isActive = isActive,
        tags = tags,
        team = team
    )
}