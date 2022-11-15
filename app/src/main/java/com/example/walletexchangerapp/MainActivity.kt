package com.example.walletexchangerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import com.example.walletexchangerapp.common.rememberExchangerAppState
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
                    topBar = {
                        ExchangerTopBar(
                            menuValues = listOf("RUB", "KZT", "UAH"),
                            selectedValue = {}
                        )
                    },
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