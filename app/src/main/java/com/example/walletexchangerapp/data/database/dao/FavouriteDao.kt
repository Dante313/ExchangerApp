package com.example.walletexchangerapp.data.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.example.walletexchangerapp.data.database.entity.FavouriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {

    @Query("SELECT * FROM favourite")
    fun getFavouriteEntitiesFlow(): Flow<List<FavouriteEntity>>

    @Query("SELECT * FROM favourite")
    suspend fun getFavouriteEntities(): List<FavouriteEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insertFavouriteEntity(favouriteEntity: FavouriteEntity)

    @Query("DELETE FROM favourite WHERE wallet = :wallet")
    suspend fun deleteFavouriteEntity(wallet: String)
}