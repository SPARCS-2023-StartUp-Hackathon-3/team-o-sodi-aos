package com.haeyum.sodi.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.haeyum.sodi.ui.main.discover.DiscoverScreen
import com.haeyum.sodi.ui.theme.SODITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            SODITheme {
                val systemUiController = rememberSystemUiController()
                DiscoverScreen()

                SideEffect {
                    systemUiController.setStatusBarColor(color = Color.Transparent)
                }
            }
        }
    }
}