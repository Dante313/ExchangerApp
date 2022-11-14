package com.example.walletexchangerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.walletexchangerapp.common.rememberExchangerAppState
import com.example.walletexchangerapp.navigation.BottomBarDestination
import com.example.walletexchangerapp.navigation.ExchangerNavHost
import com.example.walletexchangerapp.ui.presenter.screens.common.ExchangerBottomBar
import com.example.walletexchangerapp.ui.presenter.screens.common.ExchangerTopBar
import com.example.walletexchangerapp.ui.theme.WalletExchangerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appState = rememberExchangerAppState()

            WalletExchangerAppTheme {
                Scaffold(
                    topBar = { ExchangerTopBar(menuValues = emptyList(), selectedValue = {}) },
                    bottomBar = { ExchangerBottomBar(
                        destinations = appState.bottomBarDestinations,
                        onNavigateToDestination = appState::navigateToBottomBarDestination,
                        currentDestination = appState.currentDestination
                    ) }
                ) { padding ->
                    ExchangerNavHost(
                        navController = appState.navController,
                        modifier = Modifier.padding(padding)
                    )
                }
            }
        }
    }
}