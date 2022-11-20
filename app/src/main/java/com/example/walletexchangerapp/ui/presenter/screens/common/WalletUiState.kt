package com.example.walletexchangerapp.ui.presenter.screens.common

import com.example.walletexchangerapp.domain.model.RatesList

sealed interface WalletUiState {
    data class Success(val ratesList: RatesList) : WalletUiState
    object Error : WalletUiState
    object Loading : WalletUiState
}