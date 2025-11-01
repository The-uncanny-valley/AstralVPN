package com.uncannyvalley.astralvpn.domain.model

data class VpnConfig(
    val id: ConfigId,
    val name: String,
    val endpoint: ServerEndpoint,
    val protocol: Protocol,
    val auth: AuthData?,
    val splitTunneling: SplitTunnelingConfig = SplitTunnelingConfig(),
    val isActive: Boolean = false
)