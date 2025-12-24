package com.uncannyvalley.astralvpn.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uncannyvalley.astralvpn.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckEmailViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _isVerified = MutableStateFlow(false)
    val isVerified: StateFlow<Boolean> = _isVerified

    private val _uiState = MutableStateFlow(CheckEmailUiState())
    val uiState: StateFlow<CheckEmailUiState> = _uiState

    fun startVerificationCheck(email: String) {
        viewModelScope.launch {
            while (!_isVerified.value) {
                delay(3000)
                val verified = authRepository.isEmailVerified(email)
                if (verified) _isVerified.value = true
            }
        }
    }
}