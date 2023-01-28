package com.haeyum.sodi.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.haeyum.sodi.supports.safeNavigate
import com.haeyum.sodi.ui.main.navigation.MainNavRoute
import com.haeyum.sodi.ui.main.navigation.SetupMainNavGraph
import com.haeyum.sodi.ui.theme.SODITheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalAnimationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            SODITheme {
                val systemUiController = rememberSystemUiController()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding(),
                ) {
                    val navController = rememberAnimatedNavController()
                    val currentRoute by viewModel.screenState.collectAsState()

                    Box(modifier = Modifier.weight(1f)) {
                        SetupMainNavGraph(navHostController = navController)
                    }

                    BottomNavigation(
                        backgroundColor = Color.White,
                        contentColor = Color(0xFF8973D8)
                    ) {
                        BottomNavigationItem(
                            selected = true,
                            onClick = {
                                viewModel.screenState.value = MainNavRoute.Discover
                                navController.safeNavigate(MainNavRoute.Discover.route)
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Home,
                                    contentDescription = "Home",
                                    tint = if (currentRoute == MainNavRoute.Discover) Color(
                                        0xFF8973D8
                                    ) else Color(0xFFBDBDBD)
                                )
                            }
                        )
                        BottomNavigationItem(
                            selected = false,
                            onClick = {
                                viewModel.screenState.value = MainNavRoute.Write
                                navController.safeNavigate(MainNavRoute.Write.route)
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.AddCircleOutline,
                                    contentDescription = "Write",
                                    tint = if (currentRoute == MainNavRoute.Write) Color(0xFF8973D8) else Color(
                                        0xFFBDBDBD
                                    )
                                )
                            },
                        )
                        BottomNavigationItem(
                            selected = true,
                            onClick = {
                                viewModel.screenState.value = MainNavRoute.Closet
                                navController.safeNavigate(MainNavRoute.Closet.route)
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.DoorFront,
                                    contentDescription = "Closet",
                                    tint = if (currentRoute == MainNavRoute.Closet) Color(0xFF8973D8) else Color(
                                        0xFFBDBDBD
                                    )
                                )
                            },
                        )
                        BottomNavigationItem(
                            selected = true,
                            onClick = {
                                viewModel.screenState.value = MainNavRoute.Profile
                                navController.safeNavigate(MainNavRoute.Profile.route)
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.AccountCircle,
                                    contentDescription = "Profile",
                                    tint = if (currentRoute == MainNavRoute.Profile) Color(
                                        0xFF8973D8
                                    ) else Color(0xFFBDBDBD)
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