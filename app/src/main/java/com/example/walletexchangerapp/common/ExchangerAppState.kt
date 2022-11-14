package com.example.walletexchangerapp.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.walletexchangerapp.navigation.BottomBarDestination
import com.example.walletexchangerapp.ui.presenter.screens.favourite.navigation.favouriteNavigationRoute
import com.example.walletexchangerapp.ui.presenter.screens.favourite.navigation.navigateToFavourite
import com.example.walletexchangerapp.ui.presenter.screens.popular.navigation.navigateToPopular
import com.example.walletexchangerapp.ui.presenter.screens.popular.navigation.popularNavigationRoute

@Composable
fun rememberExchangerAppState(
    navController: NavHostController = rememberNavController()
): ExchangerAppState {
    return ExchangerAppState(navController)
}

@Stable
class ExchangerAppState(val navController: NavHostController) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentBottomBarDestination: BottomBarDestination?
        @Composable get() = when (currentDestination?.route) {
            popularNavigationRoute -> BottomBarDestination.POPULAR
            favouriteNavigationRoute -> BottomBarDestination.FAVOURITE
            else -> null
        }

    val bottomBarDestinations: List<BottomBarDestination> = BottomBarDestination.values().asList()

    fun navigateToBottomBarDestination(bottomBarDestination: BottomBarDestination) {
        val bottomBarNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }

        when (bottomBarDestination) {
            BottomBarDestination.POPULAR -> navController.navigateToPopular(bottomBarNavOptions)
            BottomBarDestination.FAVOURITE -> navController.navigateToFavourite(bottomBarNavOptions)
        }
    }
}