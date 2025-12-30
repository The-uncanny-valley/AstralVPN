package com.uncannyvalley.astralvpn.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uncannyvalley.astralvpn.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel(), RegisterViewModelInterface {

    private val _uiState = MutableStateFlow(RegisterUiState())
    override val uiState: StateFlow<RegisterUiState> = _uiState

    private val _events = MutableSharedFlow<RegisterEvent>(0)
    override val events: SharedFlow<RegisterEvent> = _events

    override fun onEmailChanged(value: String) {
        update { it.copy(email = value) }
    }

    override fun onNameChanged(value: String) {
        update { it.copy(name = value) }
    }

    override fun onPasswordChanged(value: String) {
        update { it.copy(password = value) }
    }

    override fun onTermsChecked(value: Boolean) {
        update { it.copy(termsAccepted = value) }
    }

    private fun update(reducer: (RegisterUiState) -> RegisterUiState) {
        _uiState.update(reducer)
    }

    override fun onRegisterClick() {
        val state = _uiState.value
        if (!state.isSubmitEnabled) return

        viewModelScope.launch {
            update { it.copy(isLoading = true) }

            val result = registerUseCase(
                state.email,
                state.name,
                state.password
            )

            if (result.isSuccess) {
                _events.emit(RegisterEvent.VerificationEmailSent)
            } else {
                update { it.copy(errorMessage = "Registration failed") }
            }
            update { it.copy(isLoading = false) }
        }
    }
}

sealed class RegisterEvent {
    data class Error(val reason: String)
    data object VerificationEmailSent : RegisterEvent()
}