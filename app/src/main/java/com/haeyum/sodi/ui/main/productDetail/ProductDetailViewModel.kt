package com.haeyum.sodi.ui.main.productDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor() : ViewModel() {
    val isFavorite = MutableStateFlow(false)
    val showBuyPopup = MutableStateFlow(false)
    val showProgressPopup = MutableStateFlow(false)
    val showCompletePopup = showProgressPopup.filter { it }.onEach { delay(2000) }.map { true }
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    init {
        viewModelScope.launch {
            showProgressPopup.collectLatest {
                if (it) {
                    delay(2000)
                    showProgressPopup.value = false
                }
            }
        }
    }
}