package com.haeyum.sodi.ui.intro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.haeyum.sodi.ui.intro.navigation.SetupIntroNavGraph
import com.haeyum.sodi.ui.main.MainActivity
import com.haeyum.sodi.ui.theme.SODITheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalAnimationApi::class)
@AndroidEntryPoint
class IntroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val systemUiController = rememberSystemUiController()
            val navController = rememberAnimatedNavController()

            SODITheme {
                SetupIntroNavGraph(navController)
            }

            SideEffect {
                systemUiController.setStatusBarColor(color = Color.Transparent)
            }
        }

        startActivity(Intent(this, MainActivity::class.java))
    }
}