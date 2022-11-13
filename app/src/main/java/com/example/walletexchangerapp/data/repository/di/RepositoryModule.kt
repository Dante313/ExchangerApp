package com.example.walletexchangerapp.data.repository.di

import com.example.walletexchangerapp.data.database.dao.FavouriteDao
import com.example.walletexchangerapp.data.database.dao.PopularDao
import com.example.walletexchangerapp.data.network.api.ExchangerApi
import com.example.walletexchangerapp.data.repository.FavouriteRepositoryImpl
import com.example.walletexchangerapp.data.repository.PopularRepositoryImpl
import com.example.walletexchangerapp.data.repository.SortRepositoryImpl
import com.example.walletexchangerapp.data.store.SortStore
import com.example.walletexchangerapp.domain.repository.FavouriteRepository
import com.example.walletexchangerapp.domain.repository.PopularRepository
import com.example.walletexchangerapp.domain.repository.SortRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePopularRepository(
        exchangerApi: ExchangerApi,
        popularDao: PopularDao
    ): PopularRepository {
        return PopularRepositoryImpl(exchangerApi, popularDao)
    }

    @Singleton
    @Provides
    fun provideFavouriteRepository(favouriteDao: FavouriteDao): FavouriteRepository {
        return FavouriteRepositoryImpl(favouriteDao)
    }

    @Singleton
    @Provides
    fun provideSortRepository(sortStore: SortStore): SortRepository {
        return SortRepositoryImpl(sortStore)
    }
}