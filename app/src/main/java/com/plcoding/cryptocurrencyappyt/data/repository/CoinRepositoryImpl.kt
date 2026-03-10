package com.plcoding.cryptocurrencyappyt.data.repository

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.local.dao.CoinDao
import com.plcoding.cryptocurrencyappyt.data.local.dao.CoinDetailDao
import com.plcoding.cryptocurrencyappyt.data.local.entity.toCoin
import com.plcoding.cryptocurrencyappyt.data.remote.CoinPaprikaApi
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi,
    private val coinDao: CoinDao,
    private val coinDetailDao: CoinDetailDao
) : CoinRepository {
    override fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())
        try {
            coinDao.getCoins().collect { entities ->
                emit(Resource.Success(entities.map { it.toCoin() }))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }

    override suspend fun getCoinById(coinId: String): CoinDetail {
        TODO("Not yet implemented")
    }
}

interface CoinRepository {
    fun getCoins(): Flow<Resource<List<Coin>>>
    suspend fun getCoinById(coinId: String): CoinDetail
}
