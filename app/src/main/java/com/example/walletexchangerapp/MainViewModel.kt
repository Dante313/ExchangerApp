package com.example.walletexchangerapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walletexchangerapp.domain.repository.PopularRepository
import com.example.walletexchangerapp.domain.repository.SortRepository
import com.example.walletexchangerapp.domain.usecase.GetFilteredWalletUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFilteredWalletUseCase: GetFilteredWalletUseCase
) : ViewModel() {

    private val _walletListStateFlow = MutableStateFlow(listOf(RUB_WALLET, KZT_WALLET, UAH_WALLET))
    val walletListStateFlow: StateFlow<List<String>> = _walletListStateFlow.asStateFlow()

    private val _selectedWalletStateFlow = MutableStateFlow(walletListStateFlow.value[0])
    val selectedWalletStateFlow: StateFlow<String> = _selectedWalletStateFlow.asStateFlow()

    private val _sortDialogStateFlow = MutableStateFlow(false)
    val sortDialogStateFlow: StateFlow<Boolean> = _sortDialogStateFlow.asStateFlow()

    private val _refreshingRatesStateFlow = MutableStateFlow(false)
    val refreshingRatesStateFlow: StateFlow<Boolean> = _refreshingRatesStateFlow.asStateFlow()

    fun selectNewWallet(wallet: String) {
        _selectedWalletStateFlow.value = wallet
    }

    fun shouldShowSortDialog(show: Boolean) {
        _sortDialogStateFlow.value = show
    }

    fun shouldRefreshRates(refresh: Boolean) {
        _refreshingRatesStateFlow.value = refresh
    }

    fun getPopularWallet(base: String) {
        viewModelScope.launch {
            getFilteredWalletUseCase.getWalletList(base)
        }
    }

    companion object {
        private const val RUB_WALLET = "RUB"
        private const val KZT_WALLET = "KZT"
        private const val UAH_WALLET = "UAH"
    }
}