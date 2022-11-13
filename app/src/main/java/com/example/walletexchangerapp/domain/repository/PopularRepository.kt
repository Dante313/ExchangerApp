package com.example.walletexchangerapp.domain.repository

import com.example.walletexchangerapp.data.database.entity.RatesEntity
import com.example.walletexchangerapp.data.database.entity.WalletEntity
import com.example.walletexchangerapp.data.network.model.RemoteWallet
import com.example.walletexchangerapp.domain.model.Rates
import com.example.walletexchangerapp.domain.model.ResponseResult
import com.example.walletexchangerapp.domain.model.Wallet
import kotlinx.coroutines.flow.Flow

interface PopularRepository {

    suspend fun getPopularWalletList(wallet: String)

    val popularWalletFlow: Flow<Wallet?>

    val ratesByWalletFlow: Flow<Rates?>

    suspend fun insertWallet(wallet: Wallet)

    suspend fun insertRates(rates: Rates, walletId: Long)

    suspend fun updateWallet(wallet: Wallet)

    suspend fun updateRates(rates: Rates, walletId: Long)

    suspend fun deleteWallet(wallet: Wallet)

    suspend fun deleteRates(rates: Rates, walletId: Long)
}