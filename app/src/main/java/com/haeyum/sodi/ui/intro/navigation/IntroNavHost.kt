package com.haeyum.sodi.ui.intro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.haeyum.sodi.ui.intro.signIn.SignInScreen
import com.haeyum.sodi.ui.intro.signUp.SignUpScreen

@Composable
fun SetupIntroNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = IntroNavRoute.SignIn.route) {
        composable(route = IntroNavRoute.SignIn.route) {
            SignInScreen(
                navigateToSignUp = {
                    navHostController.navigate(IntroNavRoute.SignUp.route)
                }
            )
        }

        composable(route = IntroNavRoute.SignUp.route) {
            SignUpScreen(onNavigateUp = navHostController::navigateUp)
        }
    }
}