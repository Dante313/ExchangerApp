package com.example.walletexchangerapp.domain.usecase

import com.example.walletexchangerapp.domain.model.RatesList
import com.example.walletexchangerapp.domain.model.RatesMap
import com.example.walletexchangerapp.domain.model.ResponseResult
import com.example.walletexchangerapp.domain.model.Wallet
import com.example.walletexchangerapp.domain.repository.FavouriteRepository
import com.example.walletexchangerapp.domain.repository.PopularRepository
import com.example.walletexchangerapp.ui.presenter.screens.common.WalletUiState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetFilteredWalletUseCase @Inject constructor(
    private val favouriteRepository: FavouriteRepository,
    private val popularRepository: PopularRepository
) {
    private val _walletResponseResultFlow = MutableSharedFlow<ResponseResult<Wallet>>(
        extraBufferCapacity = 1,
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    operator fun invoke(onlyFavourite: Boolean = false): Flow<WalletUiState> {
        return combine(
            favouriteRepository.favouriteFlow,
            _walletResponseResultFlow
        ) { favourites, walletResult ->
            when (walletResult) {
                is ResponseResult.Success -> {
                    walletResult.data?.rates?.let { ratesMap ->
                        if (onlyFavourite) {
                            val favouritesList = RatesList((ratesMap.favourites(favourites.map { it.wallet })))

                            WalletUiState.Success(
                                favouritesList.copy(rates = favouritesList.rates.filter { it.isFavourite })
                            )
                        } else {
                            WalletUiState.Success(RatesList((ratesMap.favourites(favourites.map { it.wallet }))))
                        }
                    } ?: WalletUiState.Error
                }
                is ResponseResult.Loading -> WalletUiState.Loading
                else -> WalletUiState.Error
            }

        }
    }

    suspend fun getWalletList(base: String) {
        _walletResponseResultFlow.tryEmit(popularRepository.getPopularWalletList(base))
    }

    private fun RatesMap.favourites(favouriteList: List<String>) =
        this.rates.map { if (favouriteList.contains(it.key)) it.value.copy(isFavourite = true) else it.value }
}