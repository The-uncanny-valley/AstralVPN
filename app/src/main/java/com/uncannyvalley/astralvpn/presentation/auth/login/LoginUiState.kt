package com.uncannyvalley.astralvpn.presentation.auth.login

import com.uncannyvalley.astralvpn.presentation.utils.isValidEmail

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    val isEmailValid: Boolean
        get() = isValidEmail(email)

    val isPasswordValid: Boolean
        get() = password.length >= 5

    val isSubmitEnabled: Boolean
        get() = isEmailValid &&
                isPasswordValid &&
                !isLoading
}