package com.uncannyvalley.astralvpn.domain.model

sealed class AuthData {
    data class UsernamePassword(val username: String, val password: String) : AuthData() {
        fun isValid(): Boolean = username.isNotBlank() && password.isNotBlank()
    }

    data class VlessAuth(
        val uuid: String,
        val flow: String? = null
    ) : AuthData() {
        fun isValid(): Boolean = uuid.isNotBlank()
    }
}