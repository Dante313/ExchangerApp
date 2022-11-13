package com.example.walletexchangerapp.data.database.di

import android.content.Context
import androidx.room.Room
import com.example.walletexchangerapp.data.database.WalletDatabase
import com.example.walletexchangerapp.data.database.WalletDatabase.Companion.DB_NAME
import com.example.walletexchangerapp.data.database.dao.FavouriteDao
import com.example.walletexchangerapp.data.database.dao.PopularDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideWalletDb(@ApplicationContext app: Context): WalletDatabase {
        return Room
            .databaseBuilder(
                context = app,
                klass = WalletDatabase::class.java,
                name = DB_NAME
            )
            // WARNING: только для теста
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePopularDao(database: WalletDatabase): PopularDao = database.popularDao()

    @Singleton
    @Provides
    fun provideFavouriteDao(database: WalletDatabase): FavouriteDao = database.favouriteDao()
}