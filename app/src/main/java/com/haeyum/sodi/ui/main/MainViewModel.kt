package com.haeyum.sodi.ui.main

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haeyum.sodi.ui.main.navigation.MainNavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val screenState = MutableStateFlow<MainNavRoute>(MainNavRoute.Discover)
    val takePictureBitmap = MutableSharedFlow<Bitmap>()
    val takePictureEvent = MutableSharedFlow<Unit>()

    fun takePicture() {
        viewModelScope.launch {
            takePictureEvent.emit(Unit)
        }
    }
}