package com.haeyum.sodi.ui.main.discover

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haeyum.sodi.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(postRepository: PostRepository) : ViewModel() {
    val discoverResponse = flow {
        emit(postRepository.getPostList())
    }.catch {
        Log.d("PANGMOO", it.toString())
        emit(emptyList())
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val showPopup = MutableStateFlow(false)
}