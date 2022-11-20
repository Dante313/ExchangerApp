package com.example.walletexchangerapp.ui.presenter.screens.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walletexchangerapp.domain.model.Rate
import com.example.walletexchangerapp.domain.usecase.AddOrDeleteFavouriteUseCase
import com.example.walletexchangerapp.domain.usecase.GetFilteredWalletUseCase
import com.example.walletexchangerapp.ui.presenter.screens.common.WalletUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    getFilteredWalletUseCase: GetFilteredWalletUseCase,
    private val addOrDeleteFavouriteUseCase: AddOrDeleteFavouriteUseCase
) : ViewModel() {

    val filteredUiStateFlow: StateFlow<WalletUiState> = getFilteredWalletUseCase(true).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = WalletUiState.Loading
    )

    fun addOrDeleteRateFromFavourite(rate: Rate) {
        viewModelScope.launch {
            addOrDeleteFavouriteUseCase(rate)
        }
    }
}