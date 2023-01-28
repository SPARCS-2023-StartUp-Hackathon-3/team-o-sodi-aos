package com.haeyum.sodi.ui.main.navigation

sealed class MainNavRoute(val route: String) {
    companion object {
        const val MAIN_ROUTE = "main"
    }

    object Discover : MainNavRoute(route = "discover")
    object Write : MainNavRoute(route = "write")
    object Profile : MainNavRoute(route = "profile")
    object Closet : MainNavRoute(route = "closet")
}