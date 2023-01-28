package com.haeyum.sodi.ui.main.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : ViewModel() {
    val selectedTabIndex = MutableStateFlow(0)
}