package com.example.walletexchangerapp.data.network.model

import com.example.walletexchangerapp.domain.model.Rate
import com.example.walletexchangerapp.domain.model.RatesMap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRates(
    @SerialName(value = EUR_WALLET) val eur: Double,
    @SerialName(value = GBR_WALLET) val gbr: Double,
    @SerialName(value = JPY_WALLET) val jpy: Double
) {
    companion object {
        private const val EUR_WALLET = "EUR"
        private const val GBR_WALLET = "GBR"
        private const val JPY_WALLET = "JPY"


        fun RemoteRates.toRatesMap() = RatesMap(
            rates = mapOf(
                EUR_WALLET to Rate(wallet = EUR_WALLET, amount = eur, isFavourite = false),
                GBR_WALLET to Rate(wallet = GBR_WALLET, amount = gbr, isFavourite = false),
                JPY_WALLET to Rate(wallet = JPY_WALLET, amount = jpy, isFavourite = false),
            )
        )
    }
}