package com.example.walletexchangerapp.ui.presenter.screens.sort

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.walletexchangerapp.domain.model.Sort

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SortDialog(
    onDismiss: () -> Unit,
    viewModel: SortViewModel = hiltViewModel(),
) {
    val sortState by viewModel.sortState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .padding(48.dp)
            .background(color = MaterialTheme.colors.background)
    ) {
        Dialog(onDismissRequest = onDismiss) {
            MaterialRadioButtonGroupComponent(
                selectedSort = sortState,
                onSelectedSort = { viewModel.setSort(it) }
            )
        }
    }
}

@Composable
private fun MaterialRadioButtonGroupComponent(
    selectedSort: Sort,
    onSelectedSort: (Sort) -> Unit
) {
    val radioGroupOptions = listOf(
        Sort.AMOUNT_ASC,
        Sort.AMOUNT_DESC,
        Sort.NAME_ASC,
        Sort.NAME_DESC
    )
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column {
            radioGroupOptions.forEach { sort ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (sort.title == selectedSort.title),
                        onClick = { onSelectedSort(sort) }
                    )
                    .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (sort.title == selectedSort.title),
                        onClick = { onSelectedSort(sort) }
                    )
                    Text(
                        text = sort.title,
                        style = MaterialTheme.typography.body1.merge(),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

