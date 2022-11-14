package com.example.walletexchangerapp.domain.model

import com.example.walletexchangerapp.data.database.entity.WalletEntity

data class Wallet(
    val id: Long = 0,
    val base: String,
    val rates: Rates,
) {
    companion object {

        fun Wallet.toWalletEntity() = WalletEntity(
            id = id,
            base = base
        )
    }
}