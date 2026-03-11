package com.plcoding.cryptocurrencyappyt.data.repository

import com.plcoding.cryptocurrencyappyt.common.Constants
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.local.dao.CoinDao
import com.plcoding.cryptocurrencyappyt.data.local.dao.CoinDetailDao
import com.plcoding.cryptocurrencyappyt.data.local.entity.toCoin
import com.plcoding.cryptocurrencyappyt.data.local.entity.toCoinDetail
import com.plcoding.cryptocurrencyappyt.data.remote.CoinPaprikaApi
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoinDetail
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoinDetailEntity
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoinEntity
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.coroutineScope
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
        coroutineScope {
            launch {
                try {
                    val coins = api.getCoins()
                    coinDao.deleteAll()
                    coinDao.insertAll(coins.take(Constants.COIN_LIST_LIMIT).map { it.toCoinEntity() })
                } catch (e: Exception) {
                    // Cached data may still be available from DB flow
                }
            }
            try {
                coinDao.getCoins(Constants.COIN_LIST_LIMIT).collect { entities ->
                    emit(Resource.Success(entities.map { it.toCoin() }))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Unknown error"))
            }
        }
    }

    override suspend fun getCoinById(coinId: String): CoinDetail {
        val cached = coinDetailDao.getCoinDetail(coinId)
        if (cached != null) {
            return cached.toCoinDetail()
        }
        val dto = api.getCoinById(coinId)
        coinDetailDao.insertCoinDetail(dto.toCoinDetailEntity())
        return coinDetailDao.getCoinDetail(coinId)!!.toCoinDetail()
    }
}
