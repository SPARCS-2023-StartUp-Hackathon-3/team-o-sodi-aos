package com.haeyum.sodi.ui.intro

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(): ViewModel() {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

}