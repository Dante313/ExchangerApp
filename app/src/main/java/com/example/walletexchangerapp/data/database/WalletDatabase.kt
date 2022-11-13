package com.example.walletexchangerapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.walletexchangerapp.data.database.dao.FavouriteDao
import com.example.walletexchangerapp.data.database.dao.PopularDao
import com.example.walletexchangerapp.data.database.entity.FavouriteEntity
import com.example.walletexchangerapp.data.database.entity.RatesEntity
import com.example.walletexchangerapp.data.database.entity.WalletEntity

@Database(
    entities = [WalletEntity::class, RatesEntity::class, FavouriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WalletDatabase : RoomDatabase() {

    abstract fun favouriteDao(): FavouriteDao

    abstract fun popularDao(): PopularDao

    companion object {
        val DB_NAME = "favourite_db"
    }
}