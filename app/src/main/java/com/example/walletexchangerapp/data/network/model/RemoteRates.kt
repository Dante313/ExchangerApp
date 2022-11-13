package com.example.walletexchangerapp.data.network.model

import com.example.walletexchangerapp.domain.model.Rates
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRates(
    @SerialName(value = "EUR") val eur: Double,
    @SerialName(value = "GBR") val gbr: Double,
    @SerialName(value = "JPY") val jpy: Double
) {
    companion object {

        fun RemoteRates.toRates() = Rates(
            eur = eur,
            gbr = gbr,
            jpy = jpy
        )
    }
}