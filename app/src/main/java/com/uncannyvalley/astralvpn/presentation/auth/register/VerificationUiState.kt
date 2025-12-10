package com.uncannyvalley.astralvpn.presentation.auth.register

data class VerificationUiState(
    val code: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

sealed class VerificationEvent {
    data object Success : VerificationEvent()
}
