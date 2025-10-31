package com.uncannyvalley.astralvpn.domain.model

data class Credentials(val username: String, val password: String) {
    fun isValid(): Boolean = username.isNotBlank() && password.isNotBlank()
}