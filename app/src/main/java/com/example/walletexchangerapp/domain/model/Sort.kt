package com.example.walletexchangerapp.domain.model

import androidx.annotation.StringRes
import com.example.walletexchangerapp.R

enum class Sort(@StringRes val title: Int) {
    WALLET_ASC(R.string.wallet_asc),
    WALLET_DESC(R.string.wallet_desc),
    AMOUNT_ASC(R.string.amount_asc),
    AMOUNT_DESC(R.string.amount_desc)
}