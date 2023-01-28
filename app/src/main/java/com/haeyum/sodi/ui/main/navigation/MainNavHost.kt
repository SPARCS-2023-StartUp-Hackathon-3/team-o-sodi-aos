@file:OptIn(ExperimentalAnimationApi::class)

package com.haeyum.sodi.ui.main.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.haeyum.sodi.ui.main.closet.ClosetScreen
import com.haeyum.sodi.ui.main.discover.DiscoverScreen
import com.haeyum.sodi.ui.main.profile.ProfileScreen

@Composable
fun SetupMainNavGraph(navHostController: NavHostController) {
    AnimatedNavHost(
        navController = navHostController,
        startDestination = MainNavRoute.Discover.route,
        enterTransition = { fadeIn(animationSpec = tween(200)) },
        exitTransition = { fadeOut(animationSpec = tween(200)) }
    ) {
        composable(
            route = MainNavRoute.Discover.route,
        ) {
            DiscoverScreen(modifier = Modifier.fillMaxSize())
        }

        composable(
            route = MainNavRoute.Write.route,
        ) {
            // WriteScreen()
//            ProfileScreen(modifier = Modifier.fillMaxSize())
        }

        composable(route = MainNavRoute.Closet.route) {
            ClosetScreen(modifier = Modifier.fillMaxSize())
        }

        composable(
            route = MainNavRoute.Profile.route,
        ) {
            ProfileScreen(modifier = Modifier.fillMaxSize())
        }
    }
}