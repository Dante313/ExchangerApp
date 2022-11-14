package com.example.walletexchangerapp.navigation

import androidx.annotation.StringRes
import com.example.walletexchangerapp.R
import com.example.walletexchangerapp.common.ExchangerIcons
import com.example.walletexchangerapp.common.Icon


enum class BottomBarDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    @StringRes val iconTextId: Int
) {
    POPULAR(
        selectedIcon = Icon.DrawableResourceIcon(ExchangerIcons.PopularFilled),
        unselectedIcon = Icon.DrawableResourceIcon(ExchangerIcons.Popular),
        iconTextId = R.string.popular
    ),
    FAVOURITE(
        selectedIcon = Icon.DrawableResourceIcon(ExchangerIcons.FavouriteFilled),
        unselectedIcon = Icon.DrawableResourceIcon(ExchangerIcons.Favourite),
        iconTextId = R.string.favourite
    )
}