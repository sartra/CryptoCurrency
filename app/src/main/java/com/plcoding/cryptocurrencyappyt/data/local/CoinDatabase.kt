package com.plcoding.cryptocurrencyappyt.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.plcoding.cryptocurrencyappyt.data.local.dao.CoinDao
import com.plcoding.cryptocurrencyappyt.data.local.dao.CoinDetailDao
import com.plcoding.cryptocurrencyappyt.data.local.entity.CoinDetailEntity
import com.plcoding.cryptocurrencyappyt.data.local.entity.CoinEntity

@Database(
    entities = [CoinEntity::class, CoinDetailEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CoinDatabase : RoomDatabase() {
    abstract fun coinDao(): CoinDao
    abstract fun coinDetailDao(): CoinDetailDao
}