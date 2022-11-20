package com.example.walletexchangerapp.ui.presenter.screens.popular.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.walletexchangerapp.ui.presenter.screens.favourite.FavouriteRoute
import com.example.walletexchangerapp.ui.presenter.screens.popular.PopularRoute

const val popularNavigationRoute = "popular_navigation_route"

fun NavController.navigateToPopular(navOptions: NavOptions? = null) {
    this.navigate(popularNavigationRoute, navOptions)
}

fun NavGraphBuilder.popularScreen(navController: NavController) {
    composable(route = popularNavigationRoute) {
        PopularRoute()
    }
}