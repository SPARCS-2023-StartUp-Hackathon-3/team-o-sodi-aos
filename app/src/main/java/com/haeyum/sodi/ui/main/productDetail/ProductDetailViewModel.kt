package com.haeyum.sodi.ui.main.productDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haeyum.sodi.data.api.getSpecificStore.GetSpecificStoreResponse
import com.haeyum.sodi.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
class ProductDetailViewModel @Inject constructor(private val postRepository: PostRepository) :
    ViewModel() {
    val isFavorite = MutableStateFlow(false)
    val showBuyPopup = MutableStateFlow(false)
    val showProgressPopup = MutableStateFlow(false)
    val showCompletePopup = showProgressPopup.filter { it }.onEach { delay(2000) }.map { true }
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    val productResponse = MutableStateFlow<GetSpecificStoreResponse?>(null)

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

    fun fetchProductDetail(storeId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            productResponse.emit(
                kotlin.runCatching {
                    postRepository.getSpecificStoreResponse(storeId)
                }.getOrNull()
            )
        }
    }
}