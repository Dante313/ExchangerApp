package com.example.walletexchangerapp.domain.model

data class Wallet(
    val id: Long = 0,
    val base: String,
    val rates: RatesMap,
)