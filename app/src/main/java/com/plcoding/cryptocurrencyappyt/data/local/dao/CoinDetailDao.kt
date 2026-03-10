package com.plcoding.cryptocurrencyappyt.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.plcoding.cryptocurrencyappyt.data.local.entity.CoinDetailEntity

@Dao
interface CoinDetailDao {

    @Query("SELECT * FROM coin_details WHERE coinId = :coinId")
    suspend fun getCoinDetail(coinId: String): CoinDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinDetail(coinDetail: CoinDetailEntity)

    @Query("DELETE FROM coin_details")
    suspend fun deleteAll()
}

