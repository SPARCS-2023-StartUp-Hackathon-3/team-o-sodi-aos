package com.haeyum.sodi.ui.main.closet

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClosetViewModel @Inject constructor() : ViewModel() {
    val selectedTabIndex = mutableStateOf(0)
}