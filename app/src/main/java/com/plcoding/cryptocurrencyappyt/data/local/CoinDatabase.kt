package com.plcoding.cryptocurrencyappyt.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import com.plcoding.cryptocurrencyappyt.data.local.dao.CoinDao
import com.plcoding.cryptocurrencyappyt.data.local.dao.CoinDetailDao
import com.plcoding.cryptocurrencyappyt.data.local.entity.CoinDetailEntity
import com.plcoding.cryptocurrencyappyt.data.local.entity.CoinEntity

@Database(
    entities = [CoinEntity::class, CoinDetailEntity::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = CoinDatabase.RankColumnMigration::class)
    ]
)
@TypeConverters(Converters::class)
abstract class CoinDatabase : RoomDatabase() {

    @RenameColumn.Entries(
        RenameColumn(tableName = "coins", fromColumnName = "rank", toColumnName = "coin_rank"),
        RenameColumn(tableName = "coin_details", fromColumnName = "rank", toColumnName = "coin_rank")
    )
    class RankColumnMigration : AutoMigrationSpec

    abstract fun coinDao(): CoinDao
    abstract fun coinDetailDao(): CoinDetailDao
}