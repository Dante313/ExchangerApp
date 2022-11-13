package com.example.walletexchangerapp.domain.model

import com.example.walletexchangerapp.data.database.entity.RatesEntity

data class Rates(
    val id: Long = 0,
    val eur: Double,
    val gbr: Double,
    val jpy: Double
) {
    companion object {

        fun Rates.toRatesEntity(walletId: Long) = RatesEntity(
            id = id,
            eur = eur,
            gbr = gbr,
            jpy = jpy,
            walletId = walletId
        )
    }
}