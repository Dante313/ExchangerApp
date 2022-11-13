package com.example.walletexchangerapp.domain.model

import com.example.walletexchangerapp.data.database.entity.FavouriteEntity

data class Favourite(
    val id: Long = 0,
    val wallet: String,
) {
    companion object {

        fun Favourite.toFavouriteEntity() = FavouriteEntity(
            id = id,
            wallet = wallet
        )
    }
}