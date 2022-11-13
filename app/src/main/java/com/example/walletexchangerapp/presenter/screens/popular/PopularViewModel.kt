package com.example.walletexchangerapp.presenter.screens.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walletexchangerapp.domain.model.Rates
import com.example.walletexchangerapp.domain.model.Wallet
import com.example.walletexchangerapp.domain.repository.PopularRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularRepository: PopularRepository
) : ViewModel() {

    val popularWalletStateFlow: StateFlow<Wallet?> =
        popularRepository.popularWalletFlow.stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5_000),
            initialValue = null
        )

    val ratesWalletStateFlow: StateFlow<Rates?> =
        popularRepository.ratesByWalletFlow.stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5_000),
            initialValue = null
        )

    fun getPopularWalletList(wallet: String) {
        viewModelScope.launch {
            popularRepository.getPopularWalletList(wallet)
        }
    }

    fun insertWallet(wallet: Wallet) {
        viewModelScope.launch {
            popularRepository.insertWallet(wallet)
        }
    }

    fun insertRates(rates: Rates, walletId: Long) {
        viewModelScope.launch {
            popularRepository.insertRates(rates, walletId)
        }
    }

    fun updateWallet(wallet: Wallet) {
        viewModelScope.launch {
            popularRepository.updateWallet(wallet)
        }
    }

    fun updateRates(rates: Rates, walletId: Long) {
        viewModelScope.launch {
            popularRepository.updateRates(rates, walletId)
        }
    }

    fun deleteWallet(wallet: Wallet) {
        viewModelScope.launch {
            popularRepository.deleteWallet(wallet)
        }
    }

    fun deleteRates(rates: Rates, walletId: Long) {
        viewModelScope.launch {
            popularRepository.deleteRates(rates, walletId)
        }
    }
}