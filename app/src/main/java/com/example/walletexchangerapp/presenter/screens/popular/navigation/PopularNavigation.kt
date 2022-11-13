package com.example.walletexchangerapp.presenter.screens.popular.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val popularNavigationRoute = "popular_navigation_route"

fun NavController.navigateToPopular(navOptions: NavOptions? = null) {
    this.navigate(popularNavigationRoute, navOptions)
}

fun NavGraphBuilder.popularScreen(navController: NavController) {
    composable(route = popularNavigationRoute) {

    }
}