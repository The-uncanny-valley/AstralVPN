package com.uncannyvalley.astralvpn.domain.model

import java.time.Instant

sealed class VpnStatus {
    object DISCONNECTED : VpnStatus()
    object CONNECTING : VpnStatus()
    data class CONNECTED(val connectedSince: Instant, val server: ServerEndpoint) : VpnStatus()
    data class ERROR(val error: ConnectionError, val retryable: Boolean) : VpnStatus()

    fun canConnect(): Boolean = this is DISCONNECTED || (this is ERROR && retryable)
}

enum class ConnectionError {
    SERVER_UNREACHABLE,
    AUTH_FAILED,
    NETWORK_UNAVAILABLE,
    CONFIG_INVALID
}