package com.uncannyvalley.astralvpn.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uncannyvalley.astralvpn.domain.usecase.VerificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val verificationUseCase: VerificationUseCase
) : ViewModel(), VerificationViewModelInterface {

    private val _uiState = MutableStateFlow(VerificationUiState())
    override val uiState: StateFlow<VerificationUiState> = _uiState

    private val _events = MutableSharedFlow<VerificationEvent>(extraBufferCapacity = 1)
    override val events: SharedFlow<VerificationEvent> = _events

    override fun onCodeChange(value: String) {
        _uiState.update { it.copy(code = value, errorMessage = null) }
    }

    override fun onVerifyClick() {
        val code = _uiState.value.code
        if (code.length < 4) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val result = verificationUseCase(code)

            if (result.isSuccess) {
                _events.emit(VerificationEvent.Success)
            } else {
                _uiState.update {
                    it.copy(
                        errorMessage = "Invalid code",
                        isLoading = false
                    )
                }
            }
        }
    }

}

interface VerificationViewModelInterface {
    val uiState: StateFlow<VerificationUiState>
    val events: SharedFlow<VerificationEvent>

    fun onCodeChange(value: String)
    fun onVerifyClick()
}

class FakeVerificationViewModel : VerificationViewModelInterface {
    private val _uiState = MutableStateFlow(VerificationUiState())
    override val uiState: StateFlow<VerificationUiState> = _uiState

    private val _events = MutableSharedFlow<VerificationEvent>(extraBufferCapacity = 1)
    override val events: SharedFlow<VerificationEvent> = _events

    override fun onCodeChange(value: String) {
        _uiState.value = _uiState.value.copy(code = value)
    }
    override fun onVerifyClick() {}
}