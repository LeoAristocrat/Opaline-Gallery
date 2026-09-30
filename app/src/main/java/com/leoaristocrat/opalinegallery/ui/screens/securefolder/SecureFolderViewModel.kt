package com.leoaristocrat.opalinegallery.ui.screens.securefolder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoaristocrat.opalinegallery.AppContainer
import com.leoaristocrat.opalinegallery.data.model.MediaItem
import com.leoaristocrat.opalinegallery.ui.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class SecureFolderViewModel(container: AppContainer) : ViewModel() {
    val items: StateFlow<List<MediaItem>> =
        container.secureFolderRepository.items
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    companion object {
        val Factory = viewModelFactory { container -> SecureFolderViewModel(container) }
    }
}
