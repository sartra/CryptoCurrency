package com.plcoding.cryptocurrencyappyt.di

import com.plcoding.cryptocurrencyappyt.common.Constants
import com.plcoding.cryptocurrencyappyt.data.local.dao.CoinDao
import com.plcoding.cryptocurrencyappyt.data.local.dao.CoinDetailDao
import com.plcoding.cryptocurrencyappyt.data.remote.CoinPaprikaApi
import com.plcoding.cryptocurrencyappyt.data.repository.CoinRepositoryImpl
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(
        api: CoinPaprikaApi,
        coinDao: CoinDao,
        coinDetailDao: CoinDetailDao
    ): CoinRepository {
        return CoinRepositoryImpl(api, coinDao, coinDetailDao)
    }
}