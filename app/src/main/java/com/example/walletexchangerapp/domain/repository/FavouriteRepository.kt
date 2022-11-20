package com.example.walletexchangerapp.domain.repository

import com.example.walletexchangerapp.data.database.entity.FavouriteEntity
import com.example.walletexchangerapp.domain.model.Favourite
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {

    val favouriteFlow: Flow<List<Favourite>>

    suspend fun getFavourites(): List<Favourite>

    suspend fun insertFavourite(favourite: Favourite)

    suspend fun deleteFavourite(wallet: String)
}