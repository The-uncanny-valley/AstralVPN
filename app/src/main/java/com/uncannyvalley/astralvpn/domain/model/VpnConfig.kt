package com.uncannyvalley.astralvpn.domain.model

data class VpnConfig(
    val id: ConfigId,
    val name: String,
    val endpoint: ServerEndpoint,
    val protocol: Protocol,
    val credentials: Credentials?,
    val isActive: Boolean = false
)