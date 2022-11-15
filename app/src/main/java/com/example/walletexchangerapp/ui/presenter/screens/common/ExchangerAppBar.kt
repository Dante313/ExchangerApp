package com.example.walletexchangerapp.ui.presenter.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.walletexchangerapp.common.ExchangerIcons

@Composable
fun ExchangerTopBar(
    menuValues: List<String>,
    selectedValue: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.onSurface),
        elevation = 0.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            DropDownList(
                valuesList = menuValues,
                valueToSelect = "",
                selectedValue = selectedValue,
            )
            IconButton(onClick = {  }) {
                Icon(
                    imageVector = ExchangerIcons.Menu,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun DropDownList(
    valuesList: List<String>,
    valueToSelect: String,
    selectedValue: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box {
        Row(
            modifier = Modifier.clickable { expanded = !expanded },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = valueToSelect,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(256.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = null
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            valuesList.forEach { value ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        selectedValue(value)
                    }
                ) {
                    Text(text = value, textAlign = TextAlign.Center)
                }
            }
        }
    }
}