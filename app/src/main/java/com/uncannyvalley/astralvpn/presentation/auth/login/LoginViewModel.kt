package com.uncannyvalley.astralvpn.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uncannyvalley.astralvpn.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(), LoginViewModelInterface {
    
    private val _uiState = MutableStateFlow(LoginUiState())
    override val uiState: StateFlow<LoginUiState> = _uiState
    
    private val _events = MutableSharedFlow<LoginEvent>(0)
    override val events: SharedFlow<LoginEvent> = _events

    override fun onEmailChanged(value: String) {
        update { it.copy(email = value) }
    }

    override fun onPasswordChanged(value: String) {
        update { it.copy(password = value) }
    }

    private fun update(reducer: (LoginUiState) -> LoginUiState) {
        _uiState.update(reducer)
    }

    override fun onLoginClick() {
        val state = _uiState.value
        if (!state.isSubmitEnabled) return

        viewModelScope.launch {
            update { it.copy(isLoading = true, errorMessage = null) }

            val result = loginUseCase(
                state.email,
                state.password
            )

            if (result.isSuccess) {
                _events.emit(LoginEvent.Success)
            } else {
                update {
                    it.copy(errorMessage = "Login failed")
                }
            }
            update { it.copy(isLoading = false) }
        }
    }
}

sealed class LoginEvent {
    data class Error(val reason: String)
    data object Success : LoginEvent()
}