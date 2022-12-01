package com.example.walletexchangerapp.domain.usecase.di

import com.example.walletexchangerapp.domain.repository.FavouriteRepository
import com.example.walletexchangerapp.domain.repository.PopularRepository
import com.example.walletexchangerapp.domain.repository.SortRepository
import com.example.walletexchangerapp.domain.usecase.AddOrDeleteFavouriteUseCase
import com.example.walletexchangerapp.domain.usecase.GetFilteredWalletUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetFilteredWalletUseCase(
        favouriteRepository: FavouriteRepository,
        popularRepository: PopularRepository,
        sortRepository: SortRepository
    ): GetFilteredWalletUseCase {
        return GetFilteredWalletUseCase(favouriteRepository, popularRepository, sortRepository)
    }

    @Singleton
    @Provides
    fun provideAddOrDeleteFavouriteUseCase(
        favouriteRepository: FavouriteRepository
    ): AddOrDeleteFavouriteUseCase {
        return AddOrDeleteFavouriteUseCase(favouriteRepository)
    }
}