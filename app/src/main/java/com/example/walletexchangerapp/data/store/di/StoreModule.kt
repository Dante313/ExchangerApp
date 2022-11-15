package com.example.walletexchangerapp.data.store.di

import android.content.Context
import com.example.walletexchangerapp.data.store.SortStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoreModule {

    @Singleton
    @Provides
    fun providerSortStore(@ApplicationContext context: Context): SortStore = SortStore(context)
}