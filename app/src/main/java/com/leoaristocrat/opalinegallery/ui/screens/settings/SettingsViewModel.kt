package com.leoaristocrat.opalinegallery.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoaristocrat.opalinegallery.AppContainer
import com.leoaristocrat.opalinegallery.data.settings.AccentChoice
import com.leoaristocrat.opalinegallery.data.settings.DateGranularity
import com.leoaristocrat.opalinegallery.ui.theme.ThemeMode
import com.leoaristocrat.opalinegallery.ui.viewModelFactory
import kotlinx.coroutines.launch

/** Thin write-through wrapper around SettingsRepository — every control writes immediately. */
class SettingsViewModel(private val container: AppContainer) : ViewModel() {
    private val repo = container.settingsRepository

    fun setThemeMode(v: ThemeMode) = viewModelScope.launch { repo.setThemeMode(v) }
    fun setAccent(v: AccentChoice) = viewModelScope.launch { repo.setAccent(v) }
    fun setBlurIntensity(v: com.leoaristocrat.opalinegallery.ui.theme.BlurIntensity) = viewModelScope.launch { repo.setBlurIntensity(v) }
    fun setLiquidGlassIntensity(v: com.leoaristocrat.opalinegallery.ui.theme.BlurIntensity) = viewModelScope.launch { repo.setLiquidGlassIntensity(v) }
    fun setSurfaceOpacity(v: Float) = viewModelScope.launch { repo.setSurfaceOpacity(v) }
    fun setGridColumns(v: Int) = viewModelScope.launch { repo.setGridColumns(v) }
    fun setDateGranularity(v: DateGranularity) = viewModelScope.launch { repo.setDateGranularity(v) }
    fun setHighQualityThumbnails(v: Boolean) = viewModelScope.launch { repo.setHighQualityThumbnails(v) }
    fun setPlayerSliderStyle(v: com.leoaristocrat.opalinegallery.data.settings.PlayerSliderStyle) = viewModelScope.launch { repo.setPlayerSliderStyle(v) }
    fun setLoopVideos(v: Boolean) = viewModelScope.launch { repo.setLoopVideos(v) }
    fun setSkipConfirmationsAndToasts(v: Boolean) = viewModelScope.launch { repo.setSkipConfirmationsAndToasts(v) }
    fun setEnablePipMode(v: Boolean) = viewModelScope.launch { repo.setEnablePipMode(v) }
    fun setMoveToTrashByDefaultForDelete(v: Boolean) = viewModelScope.launch { repo.setMoveToTrashByDefaultForDelete(v) }

    fun setSecureEnabled(v: Boolean) = viewModelScope.launch { repo.setSecureFolderEnabled(v) }
    fun setBiometricUnlock(v: Boolean) = viewModelScope.launch { repo.setBiometricUnlock(v) }
    fun setShowSecureInAlbums(v: Boolean) = viewModelScope.launch { repo.setShowSecureInAlbums(v) }
    fun setPin(pin: String) = viewModelScope.launch { repo.setPin(pin) }
    fun clearPin() = viewModelScope.launch { repo.clearPin() }

    companion object {
        val Factory = viewModelFactory { container -> SettingsViewModel(container) }
    }
}
