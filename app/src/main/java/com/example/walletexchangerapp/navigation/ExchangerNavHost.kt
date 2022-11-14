package com.example.walletexchangerapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.walletexchangerapp.ui.presenter.screens.favourite.navigation.favouriteScreen
import com.example.walletexchangerapp.ui.presenter.screens.popular.navigation.popularNavigationRoute
import com.example.walletexchangerapp.ui.presenter.screens.popular.navigation.popularScreen

@Composable
fun ExchangerNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = popularNavigationRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        popularScreen(navController)
        favouriteScreen(navController)
    }
}