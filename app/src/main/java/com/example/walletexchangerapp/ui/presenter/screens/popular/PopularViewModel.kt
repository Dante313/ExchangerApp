package com.example.walletexchangerapp.ui.presenter.screens.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walletexchangerapp.domain.model.*
import com.example.walletexchangerapp.domain.usecase.AddOrDeleteFavouriteUseCase
import com.example.walletexchangerapp.domain.usecase.GetFilteredWalletUseCase
import com.example.walletexchangerapp.ui.presenter.screens.common.WalletUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    getFilteredWalletUseCase: GetFilteredWalletUseCase,
    private val addOrDeleteFavouriteUseCase: AddOrDeleteFavouriteUseCase
) : ViewModel() {

    val walletUiStateFlow: StateFlow<WalletUiState> = getFilteredWalletUseCase().stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5_000),
        initialValue = WalletUiState.Loading
    )

    fun addOrDeleteRateFromFavourite(rate: Rate) {
        viewModelScope.launch {
            addOrDeleteFavouriteUseCase(rate)
        }
    }
}