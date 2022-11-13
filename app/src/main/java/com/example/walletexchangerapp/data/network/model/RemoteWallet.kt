package com.example.walletexchangerapp.data.network.model

import com.example.walletexchangerapp.data.network.model.RemoteRates.Companion.toRates
import com.example.walletexchangerapp.domain.model.Wallet
import kotlinx.serialization.Serializable

@Serializable
data class RemoteWallet(
    val base: String,
    val rates: RemoteRates
) {
    companion object {

        fun RemoteWallet.toWallet() = Wallet(
            base = base,
            rates = rates.toRates(),
            isFavourite = false
        )
    }
}