package com.example.ruralcosts.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ruralcosts.data.Settings
import com.example.ruralcosts.data.local.ILocalSettingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val localSettingsDataStore: ILocalSettingsDataStore
) : ViewModel() {

    private val _uiState = MutableStateFlow<SettingsUiState>(SettingsUiState.Loading)
    val uiState: StateFlow<SettingsUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            localSettingsDataStore.observeDataStore().collect {
                settings -> _uiState.value = SettingsUiState.Success(settings)
            }
        }
    }

    suspend fun writeSettingOne(isChecked: Boolean) {
        localSettingsDataStore.writeSettingOne(isChecked)
    }

    suspend fun writeSettingTwo(isChecked: Boolean) {
        localSettingsDataStore.writeSettingTwo(isChecked)
    }

}

sealed class SettingsUiState {
    data object Loading: SettingsUiState()
    class Success(val settings: Settings): SettingsUiState()
    class Error(val errorMessage: String): SettingsUiState()
}