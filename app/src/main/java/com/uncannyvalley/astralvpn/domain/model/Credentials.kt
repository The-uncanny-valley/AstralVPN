package com.uncannyvalley.astralvpn.domain.model

sealed class AuthData {
    abstract fun isValid(): Boolean

    data class UsernamePassword(val username: String, val password: String) : AuthData() {
        override fun isValid(): Boolean = username.isNotBlank() && password.isNotBlank()
    }

    data class VlessAuth(
        val uuid: String,
        val flow: String? = null
    ) : AuthData() {
        override fun isValid(): Boolean = uuid.isNotBlank()
    }
}