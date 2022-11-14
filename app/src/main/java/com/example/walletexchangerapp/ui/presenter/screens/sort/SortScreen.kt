package com.example.walletexchangerapp.ui.presenter.screens.sort

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.compose.DialogHost
import com.example.walletexchangerapp.domain.model.Sort

@Composable
fun ShowSortDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onPickSort: (Sort) -> Unit
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {

        }
    }
}

@Composable
private fun SortScreen(
    onDismiss: () -> Unit,
    onPickSort: (Sort) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.padding(48.dp).background(color = MaterialTheme.colors.background)
    ) {

    }
}

