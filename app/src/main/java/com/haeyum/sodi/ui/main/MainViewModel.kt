package com.haeyum.sodi.ui.main

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haeyum.sodi.data.repository.PostRepository
import com.haeyum.sodi.supports.LogHelper
import com.haeyum.sodi.ui.main.navigation.MainNavRoute
import com.haeyum.sodi.utils.BitmapUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(postRepository: PostRepository) : ViewModel() {
    val screenState = MutableStateFlow<MainNavRoute>(MainNavRoute.Discover)
    val takePictureBitmap = MutableSharedFlow<Bitmap>()
    val takePictureEvent = MutableSharedFlow<Unit>()
    val refreshTrick = MutableSharedFlow<Unit>()

    fun takePicture() {
        viewModelScope.launch {
            takePictureEvent.emit(Unit)
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            takePictureBitmap.map {
                postRepository.postUploadClothesImage(
                    "Pangmu",
                    BitmapUtils.resizeBitmap(it, 400, 400)
                )
            }.catch {
                LogHelper.print("CATCH: postUploadClothesImage - $it")
            }.collectLatest {
                refreshTrick.emit(Unit)
                LogHelper.print("SUCCESS: postUploadClothesImage")
            }
        }
    }
}