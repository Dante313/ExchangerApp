package com.example.walletexchangerapp.ui.presenter.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.walletexchangerapp.common.ExchangerIcons

@Composable
fun ExchangerTopBar(
    menuValues: List<String>,
    valueToSelect: String,
    selectedValue: (String) -> Unit,
    onNavigateToSort: () -> Unit,
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
                valueToSelect = valueToSelect,
                selectedValue = selectedValue,
            )
            IconButton(onClick = onNavigateToSort) {
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
    selectedValue: (String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var optionRowSize by remember { mutableStateOf(Size.Zero) }

    Box {
        Row(
            modifier = Modifier
                .onGloballyPositioned { coordinates -> optionRowSize = coordinates.size.toSize() }
                .clickable { expanded = !expanded },
            verticalAlignment = Alignment.CenterVertically,
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
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { optionRowSize.width.toDp() })
        ) {
            valuesList.forEach { value ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        selectedValue(value)
                    }
                ) {
                    Text(text = value, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}