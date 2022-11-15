package com.example.walletexchangerapp.domain.repository

import com.example.walletexchangerapp.domain.model.Favourite
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {

    val favouriteEntitiesFlow: Flow<List<Favourite>>

    suspend fun insertFavourite(rate: Favourite)

    suspend fun updateFavourite(favourite: Favourite)

    suspend fun deleteFavourite(walletId: Long)
}