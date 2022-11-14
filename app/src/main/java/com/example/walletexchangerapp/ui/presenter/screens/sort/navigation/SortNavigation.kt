package com.example.walletexchangerapp.ui.presenter.screens.sort.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val sortNavigationRoute = "sort_navigation_route"

fun NavController.navigateToSort(navOptions: NavOptions? = null) {
    this.navigate(sortNavigationRoute, navOptions)
}

fun NavGraphBuilder.sortScreen(navController: NavController) {
    composable(route = sortNavigationRoute) {

    }
}