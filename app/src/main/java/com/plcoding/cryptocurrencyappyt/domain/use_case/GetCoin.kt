package com.plcoding.cryptocurrencyappyt.domain.use_case

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoin @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coinDetail = repository.getCoinById(coinId)
            emit(Resource.Success(coinDetail))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message() ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}

