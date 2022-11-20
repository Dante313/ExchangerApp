package com.example.walletexchangerapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.walletexchangerapp.domain.model.Favourite


@Entity(tableName = "favourite")
data class FavouriteEntity(@PrimaryKey val wallet: String) {
    companion object {
        fun FavouriteEntity.toFavourite() = Favourite(wallet)
    }
}