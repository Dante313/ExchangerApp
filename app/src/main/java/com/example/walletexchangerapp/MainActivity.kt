package com.example.walletexchangerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.walletexchangerapp.common.rememberExchangerAppState
import com.example.walletexchangerapp.navigation.ExchangerNavHost
import com.example.walletexchangerapp.ui.presenter.screens.common.ExchangerBottomBar
import com.example.walletexchangerapp.ui.presenter.screens.common.ExchangerTopBar
import com.example.walletexchangerapp.ui.presenter.screens.sort.SortDialog
import com.example.walletexchangerapp.ui.theme.WalletExchangerAppTheme
import dagger.hilt.android.AndroidEntryPoint

private const val RUB_WALLET = "RUB"
private const val KZT_WALLET = "KZT"
private const val UAH_WALLET = "UAH"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel: MainViewModel = hiltViewModel()

            val appState = rememberExchangerAppState()
            val walletsList = listOf(RUB_WALLET, KZT_WALLET, UAH_WALLET)
            var selectedWallet by remember { mutableStateOf(walletsList[0]) }
            var sortDialogState by remember { mutableStateOf(false) }

            if (sortDialogState) {
                SortDialog(onDismiss = { sortDialogState = false })
            }

            LaunchedEffect(selectedWallet) {
                mainViewModel.getPopularWallet(selectedWallet)
            }

            WalletExchangerAppTheme {
                Scaffold(
                    topBar = {
                        ExchangerTopBar(
                            menuValues = walletsList,
                            valueToSelect = selectedWallet,
                            selectedValue = { selectedWallet = it },
                            onNavigateToSort = { sortDialogState = true }
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