package com.haeyum.sodi.ui.intro.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    val email = _email.asStateFlow()
    val password = _password.asStateFlow()

    val enabledSignIn = email.combine(password) { email, password ->
        email.matches("[a-zA-Z0-9\\s._-]+@[a-zA-Z0-9\\s.-]+\\.[a-zA-Z0-9\\s.-]+".toRegex()) && password.length >= 8
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun requestPostSignUp() {
        showErrorMessage("이메일 혹은 비밀번호를 확인해주세요.")
    }

    fun showErrorMessage(msg: String) {
        viewModelScope.launch {
            _errorMessage.value = msg
            delay(2000)
            _errorMessage.value = null
        }
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }
}