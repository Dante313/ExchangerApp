package com.example.walletexchangerapp.domain.usecase

import com.example.walletexchangerapp.domain.model.*
import com.example.walletexchangerapp.domain.repository.FavouriteRepository
import com.example.walletexchangerapp.domain.repository.PopularRepository
import com.example.walletexchangerapp.domain.repository.SortRepository
import com.example.walletexchangerapp.ui.presenter.screens.common.WalletUiState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetFilteredWalletUseCase @Inject constructor(
    private val favouriteRepository: FavouriteRepository,
    private val popularRepository: PopularRepository,
    private val sortRepository: SortRepository
) {
    private val _walletResponseResultFlow = MutableSharedFlow<ResponseResult<Wallet>>(
        extraBufferCapacity = 1,
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    operator fun invoke(onlyFavourite: Boolean = false): Flow<WalletUiState> {
        return combine(
            favouriteRepository.favouriteFlow,
            _walletResponseResultFlow,
            sortRepository.sortFlow
        ) { favourites, walletResult, sort ->
            when (walletResult) {
                is ResponseResult.Success -> {
                    walletResult.data?.rates?.let { ratesMap ->
                        val ratesList = RatesList((ratesMap.favourites(favourites.map { it.wallet })))
                        val sortedRatesList = ratesList.sortBy(sort)

                        if (onlyFavourite) {
                            WalletUiState.Success(
                                sortedRatesList.copy(rates = sortedRatesList.rates.filter { it.isFavourite })
                            )
                        } else {
                            WalletUiState.Success(sortedRatesList)
                        }
                    } ?: WalletUiState.Error
                }
                is ResponseResult.Loading -> WalletUiState.Loading
                else -> WalletUiState.Error
            }

        }
    }

    private fun RatesList.sortBy(sort: Sort): RatesList {
        return when (sort) {
            Sort.AMOUNT_ASC -> { RatesList(this.rates.sortedBy { it.amount }) }
            Sort.AMOUNT_DESC -> { RatesList(this.rates.sortedByDescending { it.amount }) }
            Sort.WALLET_ASC -> { RatesList(this.rates.sortedBy { it.wallet }) }
            Sort.WALLET_DESC -> { RatesList(this.rates.sortedByDescending { it.wallet }) }
        }
    }

    suspend fun getWalletList(base: String) {
        _walletResponseResultFlow.tryEmit(popularRepository.getPopularWalletList(base))
    }

    private fun RatesMap.favourites(favouriteList: List<String>) =
        this.rates.map { if (favouriteList.contains(it.key)) it.value.copy(isFavourite = true) else it.value }
}