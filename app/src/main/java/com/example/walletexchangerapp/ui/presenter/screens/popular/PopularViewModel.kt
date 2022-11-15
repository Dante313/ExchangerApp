package com.example.walletexchangerapp.ui.presenter.screens.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walletexchangerapp.common.asResult
import com.example.walletexchangerapp.domain.model.*
import com.example.walletexchangerapp.domain.model.RatesMap.Companion.toRatesList
import com.example.walletexchangerapp.domain.repository.FavouriteRepository
import com.example.walletexchangerapp.domain.repository.PopularRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val favouriteRepository: FavouriteRepository,
    private val popularRepository: PopularRepository
) : ViewModel() {

    private var _walletResponseResultStateFlow: MutableStateFlow<ResponseResult<Wallet>> =
        MutableStateFlow(ResponseResult.Loading)

    val popularUiStateFlow: StateFlow<PopularUiState> = popularUiStateStream().stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5_000),
        initialValue = PopularUiState.Loading
    )

    init {
        viewModelScope.launch {
            _walletResponseResultStateFlow.value = popularRepository.getPopularWalletList("RUB")
        }
    }

    private fun popularUiStateStream(): Flow<PopularUiState> {
        val favouriteFlow = favouriteRepository.favouriteEntitiesFlow

        return combine(
            favouriteFlow,
            _walletResponseResultStateFlow
        ) { favourites, walletResult ->
            when (walletResult) {
                is ResponseResult.Success -> {
                    walletResult.data?.rates?.let { ratesMap ->
                        PopularUiState.Success(RatesList((ratesMap.favourites(favourites.map { it.wallet }))))
                    } ?: PopularUiState.Error
                }
                is ResponseResult.Loading -> PopularUiState.Loading
                else -> PopularUiState.Error
            }

        }
    }

    private fun RatesMap.favourites(favouriteList: List<String>) =
        this.rates
            .map { if (favouriteList.contains(it.key)) it.value.copy(isFavourite = true) else it.value }
}

sealed interface PopularUiState {
    data class Success(val ratesList: RatesList) : PopularUiState
    object Error : PopularUiState
    object Loading : PopularUiState
}