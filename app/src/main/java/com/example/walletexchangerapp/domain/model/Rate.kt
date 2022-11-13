package com.example.walletexchangerapp.domain.model

data class Rate(
    val wallet: String,
    val amount: Double,
    val isFavourite: Boolean
)