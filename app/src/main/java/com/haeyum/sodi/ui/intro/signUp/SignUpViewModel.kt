package com.haeyum.sodi.ui.intro.signUp

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private val _email = MutableStateFlow("")
    private val _username = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _bio = MutableStateFlow("")

    val email = _email.asStateFlow()
    val username = _username.asStateFlow()
    val password = _password.asStateFlow()
    val bio = _bio.asStateFlow()

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setUsername(username: String) {
        _username.value = username
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setBio(bio: String) {
        _bio.value = bio
    }
}