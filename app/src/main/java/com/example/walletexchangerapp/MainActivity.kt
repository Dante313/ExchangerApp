package com.example.walletexchangerapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.walletexchangerapp.common.rememberExchangerAppState
import com.example.walletexchangerapp.navigation.ExchangerNavHost
import com.example.walletexchangerapp.ui.presenter.screens.common.ExchangerBottomBar
import com.example.walletexchangerapp.ui.presenter.screens.common.ExchangerTopBar
import com.example.walletexchangerapp.ui.presenter.screens.sort.SortDialog
import com.example.walletexchangerapp.ui.theme.WalletExchangerAppTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalLifecycleComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel: MainViewModel = hiltViewModel()
            val appState = rememberExchangerAppState()
            val sortDialogState by mainViewModel.sortDialogStateFlow.collectAsStateWithLifecycle()
            val selectedWalletState by mainViewModel.selectedWalletStateFlow.collectAsStateWithLifecycle()
            val refreshingState by mainViewModel.refreshingRatesStateFlow.collectAsStateWithLifecycle()
            val walletsListState by mainViewModel.walletListStateFlow.collectAsStateWithLifecycle()

            if (sortDialogState) {
                SortDialog(onDismiss = { mainViewModel.shouldShowSortDialog(false) })
            }

            LaunchedEffect(selectedWalletState) {
                mainViewModel.getPopularWallet(selectedWalletState)
            }

            LaunchedEffect(refreshingState) {
                if (refreshingState) {
                    mainViewModel.getPopularWallet(selectedWalletState)
                    delay(3000)
                    mainViewModel.shouldRefreshRates(false)
                }
            }

            WalletExchangerAppTheme {
                Scaffold(
                    topBar = {
                        ExchangerTopBar(
                            menuValues = walletsListState,
                            valueToSelect = selectedWalletState,
                            selectedValue = { mainViewModel.selectNewWallet(it) },
                            onNavigateToSort = { mainViewModel.shouldShowSortDialog(true) }
                        )
                    },
                    bottomBar = { ExchangerBottomBar(
                        destinations = appState.bottomBarDestinations,
                        onNavigateToDestination = appState::navigateToBottomBarDestination,
                        currentDestination = appState.currentDestination
                    ) }
                ) { padding ->

                    SwipeRefresh(
                        state = rememberSwipeRefreshState(isRefreshing = refreshingState),
                        onRefresh = { mainViewModel.shouldRefreshRates(true) }
                    ) {
                        ExchangerNavHost(
                            navController = appState.navController,
                            modifier = Modifier.padding(padding)
                        )
                    }
                }
            }
        }
    }
}