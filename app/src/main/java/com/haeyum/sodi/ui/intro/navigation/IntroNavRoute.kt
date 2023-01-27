package com.haeyum.sodi.ui.intro.navigation

sealed class IntroNavRoute(val route: String) {
    companion object {
        const val INTRO_ROUTE = "intro"
    }

    object SignIn : IntroNavRoute(route = "signIn")
    object SignUp : IntroNavRoute(route = "signUp")
}