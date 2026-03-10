package com.plcoding.cryptocurrencyappyt.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.plcoding.cryptocurrencyappyt.data.remote.dto.TeamMember


class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: List<String>): String = gson.toJson(value)

    @TypeConverter
    fun toStringList(value: String): List<String> =
        gson.fromJson(value, object : TypeToken<List<String>>() {}.type)

    @TypeConverter
    fun fromTeamMemberList(value: List<TeamMember>): String = gson.toJson(value)

    @TypeConverter
    fun toTeamMemberList(value: String): List<TeamMember> =
        gson.fromJson(value, object : TypeToken<List<TeamMember>>() {}.type)
}