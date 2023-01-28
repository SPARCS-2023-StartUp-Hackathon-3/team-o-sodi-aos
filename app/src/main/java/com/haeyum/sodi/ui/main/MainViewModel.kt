package com.haeyum.sodi.ui.main

import androidx.lifecycle.ViewModel
import com.haeyum.sodi.ui.main.navigation.MainNavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val screenState = MutableStateFlow<MainNavRoute>(MainNavRoute.Discover)
}