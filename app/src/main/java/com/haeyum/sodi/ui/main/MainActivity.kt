package com.haeyum.sodi.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.DoorFront
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.haeyum.sodi.ui.main.discover.DiscoverScreen
import com.haeyum.sodi.ui.main.profile.ProfileScreen
import com.haeyum.sodi.ui.theme.SODITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            SODITheme {
                val systemUiController = rememberSystemUiController()

                Column(modifier = Modifier.fillMaxSize().navigationBarsPadding()) {
//                    DiscoverScreen(modifier = Modifier.weight(1f))
                    ProfileScreen(modifier = Modifier.weight(1f))

                    BottomNavigation(backgroundColor = Color.White) {
                        BottomNavigationItem(
                            selected = true,
                            onClick = { /*TODO*/ },
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Home,
                                    contentDescription = "Home",
                                    tint = Color(0xFF8973D8)
                                )
                            },
                        )
                        BottomNavigationItem(
                            selected = true,
                            onClick = { /*TODO*/ },
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.AddCircleOutline,
                                    contentDescription = "Profile"
                                )
                            },
                        )
                        BottomNavigationItem(
                            selected = true,
                            onClick = { /*TODO*/ },
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.DoorFront,
                                    contentDescription = "Closet"
                                )
                            },
                        )
                        BottomNavigationItem(
                            selected = true,
                            onClick = { /*TODO*/ },
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.AccountCircle,
                                    contentDescription = "Closet"
                                )
                            },
                        )
                    }

                    SideEffect {
                        systemUiController.setStatusBarColor(color = Color.Transparent)
                    }
                }
            }
        }
    }
}