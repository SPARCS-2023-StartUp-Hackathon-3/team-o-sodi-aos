package com.haeyum.sodi.ui.main.closet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haeyum.sodi.data.api.getPurchase.GetPurchaseResponse
import com.haeyum.sodi.data.repository.PostRepository
import com.haeyum.sodi.supports.LogHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ClosetViewModel @Inject constructor(postRepository: PostRepository) : ViewModel() {
    val selectedTabIndex = MutableStateFlow(0)
    val closetResponse = flow {
        emit(postRepository.getCloset())
    }.catch {
        LogHelper.print(it)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)
    val receipts = flow {
        emit(postRepository.getPurchase())
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val closets = combine(closetResponse.filterNotNull(), selectedTabIndex) { response, index ->
        response.closet.filter {
            when (index) {
                0 -> true
                1 -> it.purchase
                else -> !it.purchase
            }
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val showReceipt = MutableStateFlow<GetPurchaseResponse?>(null)
}