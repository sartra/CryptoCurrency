package com.plcoding.cryptocurrencyappyt.di

import android.content.Context
import androidx.room.Room
import com.plcoding.cryptocurrencyappyt.data.local.CoinDatabase
import com.plcoding.cryptocurrencyappyt.data.local.dao.CoinDao
import com.plcoding.cryptocurrencyappyt.data.local.dao.CoinDetailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCoinDatabase(@ApplicationContext context: Context): CoinDatabase =
        Room.databaseBuilder(context, CoinDatabase::class.java, "coin_database")
            .fallbackToDestructiveMigration(false)
            .build()

    @Provides
    @Singleton
    fun provideCoinDao(db: CoinDatabase): CoinDao = db.coinDao()

    @Provides
    @Singleton
    fun provideCoinDetailDao(db: CoinDatabase): CoinDetailDao = db.coinDetailDao()

}