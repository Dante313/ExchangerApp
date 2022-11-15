package com.example.walletexchangerapp.navigation

import androidx.annotation.StringRes
import com.example.walletexchangerapp.R
import com.example.walletexchangerapp.common.ExchangerIcons
import com.example.walletexchangerapp.common.Icon


enum class BottomBarDestination(
    val icon: Icon,
    @StringRes val iconTextId: Int
) {
    POPULAR(
        icon = Icon.ImageVectorIcon(ExchangerIcons.Popular),
        iconTextId = R.string.popular
    ),
    FAVOURITE(
        icon = Icon.ImageVectorIcon(ExchangerIcons.Favourite),
        iconTextId = R.string.favourite
    )
}