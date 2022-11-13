package com.example.walletexchangerapp.presenter.screens.favourite

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.walletexchangerapp.R
import com.example.walletexchangerapp.domain.model.Rate
import com.example.walletexchangerapp.presenter.screens.common.RatesList
import com.example.walletexchangerapp.presenter.screens.popular.PopularViewModel

@Composable
fun PopularScreen(viewModel: PopularViewModel) {
    Surface {
        RatesList(rates = , onAddedToFavourite = {  })
    }
}