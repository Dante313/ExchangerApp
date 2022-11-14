package com.example.walletexchangerapp.ui.presenter.screens.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walletexchangerapp.common.asResult
import com.example.walletexchangerapp.domain.model.Favourite
import com.example.walletexchangerapp.domain.model.Rates
import com.example.walletexchangerapp.domain.model.Wallet
import com.example.walletexchangerapp.domain.repository.FavouriteRepository
import com.example.walletexchangerapp.domain.repository.PopularRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularRepository: PopularRepository,
    private val favouriteRepository: FavouriteRepository
) : ViewModel() {

//    val popularWalletStateFlow: StateFlow<Wallet?> =
//        popularRepository.popularWalletFlow.stateIn(
//            scope = viewModelScope,
//            started = WhileSubscribed(5_000),
//            initialValue = null
//        )

    val ratesWalletFlow: Flow<Rates?> = popularRepository.ratesByWalletFlow

    private val favouriteFlow: Flow<List<Favourite>> = favouriteRepository.favouriteEntitiesFlow

//    val ratesWithFavouritesStateFlow = combine(ratesWalletFlow, favouriteFlow) { rates, favourites ->
//        val ratedWallets = listOf(rates?.eur, rates?.gbr, rates?.jpy)
//
//        ratedWallets.filter { it?.wallet == favourites. }
//
//        favourites.forEach { favourite ->
//
//        }
//    }

    private fun popularUiStateStream(

    ) {
        val favouriteFlow = favouriteRepository.favouriteEntitiesFlow
        val ratesWalletFlow = popularRepository.ratesByWalletFlow

        combine(
            favouriteFlow,
            ratesWalletFlow
        ) { favourites, rates ->
            val ratesList = rates?.toList()

            favourites.forEach { favourite ->
                ratesList?.filter { it.wallet == favourite.wallet }
            }

        }.asResult().map {

        }
    }

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

    private fun Rates.toList() = listOf(eur, gbr, jpy)
}

sealed interface PopularUiState {
    data class Success(val wallet: Wallet) : PopularUiState
    object Error : PopularUiState
    object Loading : PopularUiState
}