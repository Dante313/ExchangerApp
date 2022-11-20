package com.example.walletexchangerapp.domain.model

import com.example.walletexchangerapp.data.database.entity.FavouriteEntity

data class Favourite(val wallet: String, ) {
    companion object {
        fun Favourite.toFavouriteEntity() = FavouriteEntity(wallet)
    }
}