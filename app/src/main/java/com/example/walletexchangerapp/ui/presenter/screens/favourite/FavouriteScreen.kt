package com.example.walletexchangerapp.ui.presenter.screens.favourite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.walletexchangerapp.R
import com.example.walletexchangerapp.domain.model.Rate
import com.example.walletexchangerapp.ui.presenter.screens.common.RatesList
import com.example.walletexchangerapp.ui.presenter.screens.common.WalletUiState
import com.example.walletexchangerapp.ui.presenter.screens.popular.PopularViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun FavouriteRoute(
    viewModel: FavouriteViewModel = hiltViewModel()
) {
    val walletUiState: WalletUiState by viewModel.filteredUiStateFlow.collectAsStateWithLifecycle()

    FavouriteScreen(
        walletUiState = walletUiState,
        onWalletAddedToFavourite = { viewModel.addOrDeleteRateFromFavourite(it) }
    )
}

@Composable
fun FavouriteScreen(walletUiState: WalletUiState, onWalletAddedToFavourite: (Rate) -> Unit) {
    when (walletUiState) {
        WalletUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        WalletUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = stringResource(id = R.string.error_message), style = MaterialTheme.typography.body1)
            }
        }
        is WalletUiState.Success -> {
            RatesList(
                ratesList = walletUiState.ratesList,
                onAddedOrDeleted = onWalletAddedToFavourite
            )
        }
    }
}