package com.plcoding.cryptocurrencyappyt.data.local.entity

import androidx.room.ColumnInfo
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
    @ColumnInfo(name = "coin_rank") val rank: Int,
    val isActive: Boolean,
    val tagsJson: String,
    val teamJson: String
)

fun CoinDetailEntity.toCoinDetail(): CoinDetail {
    val gson = Gson()
    val tags: List<String> = if (tagsJson.isBlank()) emptyList()
        else gson.fromJson(tagsJson, object : TypeToken<List<String>>() {}.type) ?: emptyList()
    val teamType = TypeToken.getParameterized(List::class.java, TeamMember::class.java).type
    val team: List<TeamMember> = if (teamJson.isBlank()) emptyList()
        else gson.fromJson(teamJson, teamType) ?: emptyList()
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