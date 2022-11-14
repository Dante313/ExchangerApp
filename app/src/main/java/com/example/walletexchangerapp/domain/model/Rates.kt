package com.example.walletexchangerapp.domain.model

import com.example.walletexchangerapp.data.database.entity.RatesEntity

data class Rates(
    val id: Long = 0,
    val eur: Rate,
    val gbr: Rate,
    val jpy: Rate
) {
    companion object {

        fun Rates.toRatesEntity(walletId: Long) = RatesEntity(
            id = id,
            eur = eur.amount,
            gbr = gbr.amount,
            jpy = jpy.amount,
            walletId = walletId
        )
    }
}