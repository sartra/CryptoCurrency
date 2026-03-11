package com.plcoding.cryptocurrencyappyt.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.plcoding.cryptocurrencyappyt.data.local.entity.CoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

    @Query("SELECT * FROM coins ORDER BY coin_rank ASC LIMIT :limit")
    fun getCoins(limit: Int): Flow<List<CoinEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(coins: List<CoinEntity>)

    @Query("DELETE FROM coins")
    suspend fun deleteAll()
}
