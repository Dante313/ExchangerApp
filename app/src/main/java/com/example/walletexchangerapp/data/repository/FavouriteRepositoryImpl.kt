package com.example.walletexchangerapp.data.repository

import com.example.walletexchangerapp.data.database.dao.FavouriteDao
import com.example.walletexchangerapp.data.database.entity.FavouriteEntity.Companion.toFavourite
import com.example.walletexchangerapp.domain.model.Favourite
import com.example.walletexchangerapp.domain.model.Favourite.Companion.toFavouriteEntity
import com.example.walletexchangerapp.domain.repository.FavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val favouriteDao: FavouriteDao
    ) : FavouriteRepository {

    override val favouriteFlow: Flow<List<Favourite>> =
        favouriteDao.getFavouriteEntitiesFlow().map { favourites ->
            favourites.map { favourite -> favourite.toFavourite() }
        }

    override suspend fun getFavourites(): List<Favourite> {
        return withContext(Dispatchers.IO) {
            favouriteDao.getFavouriteEntities().map { it.toFavourite() }
        }
    }

    override suspend fun insertFavourite(favourite: Favourite) {
        withContext(Dispatchers.IO) {
            favouriteDao.insertFavouriteEntity(favourite.toFavouriteEntity())
        }
    }

    override suspend fun deleteFavourite(wallet: String) = withContext(Dispatchers.IO) {
        favouriteDao.deleteFavouriteEntity(wallet)
    }
}