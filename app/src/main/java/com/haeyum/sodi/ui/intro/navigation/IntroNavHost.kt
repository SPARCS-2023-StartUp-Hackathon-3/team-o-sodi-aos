@file:OptIn(ExperimentalAnimationApi::class)

package com.haeyum.sodi.ui.intro.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.haeyum.sodi.supports.safeNavigate
import com.haeyum.sodi.ui.intro.signIn.SignInScreen
import com.haeyum.sodi.ui.intro.signUp.SignUpScreen

@Composable
fun SetupIntroNavGraph(navHostController: NavHostController) {
    AnimatedNavHost(
        navController = navHostController,
        startDestination = IntroNavRoute.SignIn.route
    ) {
        composable(
            route = IntroNavRoute.SignIn.route,
            enterTransition = { fadeIn(tween(400)) },
            exitTransition = { fadeOut(tween(400)) },
        ) {
            SignInScreen(
                navigateToSignUp = {
                    navHostController.safeNavigate(IntroNavRoute.SignUp.route)
                }
            )
        }

        composable(
            route = IntroNavRoute.SignUp.route,
            enterTransition = { fadeIn(tween(400)) },
            exitTransition = { fadeOut(tween(400)) },
        ) {
            SignUpScreen(onNavigateUp = navHostController::navigateUp)
        }
    }
}