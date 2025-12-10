package com.uncannyvalley.astralvpn.presentation.auth.register

data class RegisterUiState(
    val email: String = "",
    val name: String = "",
    val password: String = "",
    val termsAccepted: Boolean = false,
    val isLoading: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val errorMessage: String? = null
)