package com.example.walletexchangerapp.domain.repository

import com.example.walletexchangerapp.domain.model.ResponseResult
import com.example.walletexchangerapp.domain.model.Wallet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface PopularRepository {

    suspend fun getPopularWalletList(base: String): ResponseResult<Wallet>
}