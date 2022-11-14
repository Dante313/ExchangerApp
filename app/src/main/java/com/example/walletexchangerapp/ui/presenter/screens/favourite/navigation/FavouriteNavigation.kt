package com.example.walletexchangerapp.ui.presenter.screens.favourite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val favouriteNavigationRoute = "favourite_navigation_route"

fun NavController.navigateToFavourite(navOptions: NavOptions? = null) {
    this.navigate(favouriteNavigationRoute, navOptions)
}

fun NavGraphBuilder.favouriteScreen(navController: NavController) {
    composable(route = favouriteNavigationRoute) {

    }
}