package com.example.walletexchangerapp.domain.model

data class RatesList(val rates: List<Rate>)

data class RatesMap(val rates: Map<String, Rate>) {
    companion object {
        fun RatesMap.toRatesList() = RatesList(rates.values.toList())
    }
}

data class Rate(
    val wallet: String,
    val amount: Double,
    val isFavourite: Boolean
)