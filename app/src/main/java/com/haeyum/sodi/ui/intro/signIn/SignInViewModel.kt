package com.haeyum.sodi.ui.intro.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    val email = _email.asStateFlow()
    val password = _password.asStateFlow()

    val enabledSignIn = email.combine(password) { email, password ->
        Regex("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$").matches(email) && password.length >= 8
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun requestPostSignUp() {
        println("${email.value}|${password.value}")
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }
}