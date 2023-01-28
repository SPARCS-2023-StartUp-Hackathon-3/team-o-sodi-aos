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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClosetViewModel @Inject constructor(postRepository: PostRepository) : ViewModel() {
    val selectedTabIndex = MutableStateFlow(0)

    val refresh = MutableSharedFlow<Unit>()
    val closetResponse = refresh.map {
        postRepository.getCloset()
    }
        .catch {
            LogHelper.print(it)
        }
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

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

    fun refreshClosets() {
        viewModelScope.launch {
            refresh.emit(Unit)
        }
    }

    init {
        refreshClosets()
    }
}