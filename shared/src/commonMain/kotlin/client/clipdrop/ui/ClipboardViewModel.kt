package client.clipdrop.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import client.clipdrop.ClipboardActions
import client.clipdrop.data.ClipboardRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ClipboardViewModel(
    private val clipboardActions: ClipboardActions,
    clipboardRepository: ClipboardRepository,
) : ViewModel() {

    val state: StateFlow<ClipboardUiState> = clipboardRepository.watchClipboard()
        .map<String, ClipboardUiState> { text ->
            clipboardActions.write(text)
            ClipboardUiState.Success(text)
        }
        .catch { error -> emit(ClipboardUiState.Error(error.message ?: "Failed to connect to ClipDrop backend")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ClipboardUiState.Connecting,
        )
}

sealed interface ClipboardUiState {
    data object Connecting : ClipboardUiState
    data class Success(val text: String) : ClipboardUiState
    data class Error(val message: String) : ClipboardUiState
}
