package com.example.walletexchangerapp.data.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.example.walletexchangerapp.data.database.entity.FavouriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {

    @Query("SELECT * FROM favourite")
    fun getFavouriteEntitiesFlow(): Flow<List<FavouriteEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insertFavouriteEntity(favouriteEntity: FavouriteEntity)

    @Update
    suspend fun updateFavouriteEntity(favouriteEntity: FavouriteEntity)

    @Query("DELETE FROM favourite WHERE id = :walletId")
    suspend fun deleteFavouriteEntity(walletId: Long)
}