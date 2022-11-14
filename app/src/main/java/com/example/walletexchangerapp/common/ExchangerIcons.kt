package com.example.walletexchangerapp.common

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.walletexchangerapp.R

object ExchangerIcons {
    val Popular = R.drawable.ic_popular
    val PopularFilled = R.drawable.ic_popular_filled
    val Favourite = R.drawable.ic_favourite
    val FavouriteFilled = R.drawable.ic_favourite_filled
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}