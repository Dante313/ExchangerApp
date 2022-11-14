package com.example.walletexchangerapp.ui.presenter.screens.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.walletexchangerapp.R

@Composable
fun RatesList(rates: List<Rate>, onAddedToFavourite: (Rate) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        rates.forEach { rate ->
            item {
                RateItem(
                    rate = rate,
                    onAddedToFavourite = { onAddedToFavourite(rate) },
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun RateItem(
    rate: Rate,
    onAddedToFavourite: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = stringResource(R.string.wallet_rate, rate.wallet, rate.amount),
            style = MaterialTheme.typography.body1
        )
        IconButton(onClick = onAddedToFavourite) {
            if (rate.isFavourite) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "")
            } else {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    }
}