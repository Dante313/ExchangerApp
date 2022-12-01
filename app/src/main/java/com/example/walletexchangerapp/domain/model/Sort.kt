package com.example.walletexchangerapp.domain.model

enum class Sort(val title: String) {
    NAME_ASC("Название по возрастанию"),
    NAME_DESC("Название по убыванию"),
    AMOUNT_ASC("Цена по возрастанию"),
    AMOUNT_DESC("Цена по убыванию")
}