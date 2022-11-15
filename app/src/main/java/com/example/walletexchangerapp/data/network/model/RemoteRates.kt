package com.example.walletexchangerapp.data.network.model

import com.example.walletexchangerapp.domain.model.Rate
import com.example.walletexchangerapp.domain.model.RatesMap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRates(
    @SerialName(value = EUR_WALLET) val eur: Double,
    @SerialName(value = CNY_WALLET) val cny: Double,
    @SerialName(value = JPY_WALLET) val jpy: Double,
    @SerialName(value = CHF_WALLET) val chf: Double,
    @SerialName(value = GBP_WALLET) val gbp: Double
) {
    companion object {
        private const val EUR_WALLET = "EUR"
        private const val CNY_WALLET = "CNY"
        private const val JPY_WALLET = "JPY"
        private const val CHF_WALLET = "CHF"
        private const val GBP_WALLET = "GBP"


        fun RemoteRates.toRatesMap() = RatesMap(
            rates = mapOf(
                EUR_WALLET to Rate(wallet = EUR_WALLET, amount = eur, isFavourite = false),
                CNY_WALLET to Rate(wallet = CNY_WALLET, amount = cny, isFavourite = false),
                JPY_WALLET to Rate(wallet = JPY_WALLET, amount = jpy, isFavourite = false),
                CHF_WALLET to Rate(wallet = CHF_WALLET, amount = chf, isFavourite = false),
                GBP_WALLET to Rate(wallet = GBP_WALLET, amount = gbp, isFavourite = false),
            )
        )
    }
}