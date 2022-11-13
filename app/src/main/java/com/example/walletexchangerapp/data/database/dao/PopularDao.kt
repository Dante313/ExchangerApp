package com.example.walletexchangerapp.data.database.dao

import androidx.room.*
import com.example.walletexchangerapp.data.database.entity.RatesEntity
import com.example.walletexchangerapp.data.database.entity.WalletEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularDao {

    @Query("SELECT * FROM popular")
    fun getPopularWalletEntityFlow(): Flow<WalletEntity>

    @Query("SELECT * FROM rates WHERE wallet_id = :walletId")
    fun getRatesByWalletEntityFlow(): Flow<RatesEntity?>

    @Query("SELECT * FROM rates WHERE wallet_id = :walletId")
    suspend fun getRatesByWalletEntity(walletId: Long): RatesEntity?

    @Insert
    suspend fun insertWalletEntity(walletEntity: WalletEntity): Long

    @Insert
    suspend fun insertRatesEntity(ratesEntity: RatesEntity)

    @Update
    suspend fun updateWalletEntity(walletEntity: WalletEntity)

    @Update
    suspend fun updateRatesEntity(ratesEntity: RatesEntity)

    @Delete
    suspend fun deleteWalletEntity(walletEntity: WalletEntity)

    @Delete
    suspend fun deleteRatesEntity(ratesEntity: RatesEntity)
}