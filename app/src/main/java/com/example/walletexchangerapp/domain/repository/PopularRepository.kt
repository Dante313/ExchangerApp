package com.example.walletexchangerapp.domain.repository

import com.example.walletexchangerapp.domain.model.ResponseResult
import com.example.walletexchangerapp.domain.model.Wallet

interface PopularRepository {

    suspend fun getPopularWalletList(base: String): ResponseResult<Wallet>
}