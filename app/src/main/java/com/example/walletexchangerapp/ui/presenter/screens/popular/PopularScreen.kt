package com.example.walletexchangerapp.ui.presenter.screens.popular

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.walletexchangerapp.ui.presenter.screens.common.RatesList

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun PopularRoute(
    modifier: Modifier,
    viewModel: PopularViewModel = hiltViewModel()
) {
    val popularUiState: PopularUiState by viewModel.popularUiStateFlow.collectAsStateWithLifecycle()

    PopularScreen(popularUiState)
}

@Composable
fun PopularScreen(popularUiState: PopularUiState) {
    when (popularUiState) {
        PopularUiState.Loading -> {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        PopularUiState.Error -> {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(text = stringResource(id = R.string.error_message), style = MaterialTheme.typography.body1)
            }
        }
        is PopularUiState.Success -> {
            RatesList(ratesList = popularUiState.ratesList, onAddedToFavourite = {})
        }
    }
}