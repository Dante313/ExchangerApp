package com.example.walletexchangerapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.walletexchangerapp.domain.model.Rates
import com.example.walletexchangerapp.domain.model.Wallet

@Entity(tableName = "wallet")
data class WalletEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val base: String
) {
    companion object {

        fun WalletEntity.toWallet(rates: Rates) = Wallet(
            id = id,
            base = base,
            rates = rates,
            isFavourite = false
        )
    }
}