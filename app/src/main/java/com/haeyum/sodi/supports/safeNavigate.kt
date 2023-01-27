package com.haeyum.sodi.supports

import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

fun NavController.safeNavigate(
    route: String,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    if (this.currentDestination?.route != route)
        navigate(
            NavDeepLinkRequest.Builder.fromUri(NavDestination.createRoute(route).toUri()).build(),
            navOptions,
            navigatorExtras
        )
}