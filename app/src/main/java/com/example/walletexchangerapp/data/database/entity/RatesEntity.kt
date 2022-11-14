package com.example.walletexchangerapp.data.database.entity

import androidx.room.*
import com.example.walletexchangerapp.domain.model.Rate
import com.example.walletexchangerapp.domain.model.Rates

@Entity(
    tableName = "rates",
    foreignKeys = [
        ForeignKey(
            entity = WalletEntity::class,
            parentColumns = ["id"],
            childColumns = ["wallet_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("wallet_id")
    ]
)
data class RatesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "eur")
    val eur: Double,

    @ColumnInfo(name = "gbr")
    val gbr: Double,

    @ColumnInfo(name = "jpy")
    val jpy: Double,

    @ColumnInfo(name = "wallet_id")
    val walletId: Long
) {
    companion object {

        fun RatesEntity.toRates() = Rates(
            eur = Rate(wallet = "EUR", amount = eur, isFavourite = false),
            gbr = Rate(wallet = "GBR", amount = gbr, isFavourite = false),
            jpy = Rate(wallet = "JPY", amount = jpy, isFavourite = false)
        )
    }
}