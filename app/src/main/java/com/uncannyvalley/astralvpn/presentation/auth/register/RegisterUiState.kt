package com.uncannyvalley.astralvpn.presentation.auth.register

import com.uncannyvalley.astralvpn.presentation.utils.isValidEmail

data class RegisterUiState(
    val email: String = "",
    val name: String = "",
    val password: String = "",
    val termsAccepted: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    val isEmailValid: Boolean
        get() = isValidEmail(email)

    val isPasswordValid: Boolean
        get() = password.length >= 8

    val isSubmitEnabled: Boolean
        get() = isEmailValid &&
                name.isNotBlank() &&
                isPasswordValid &&
                termsAccepted &&
                !isLoading
}