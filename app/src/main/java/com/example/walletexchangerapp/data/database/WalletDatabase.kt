package com.example.walletexchangerapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.walletexchangerapp.data.database.dao.FavouriteDao
import com.example.walletexchangerapp.data.database.entity.FavouriteEntity

@Database(
    entities = [FavouriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WalletDatabase : RoomDatabase() {

    abstract fun favouriteDao(): FavouriteDao

    companion object {
        val DB_NAME = "favourite_db"
    }
}