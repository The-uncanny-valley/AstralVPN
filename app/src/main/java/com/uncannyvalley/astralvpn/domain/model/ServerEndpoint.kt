package com.uncannyvalley.astralvpn.domain.model

data class ServerEndpoint(val host: String, val port: Int) {
    fun isValid(): Boolean = host.isNotBlank() && port in 1..65535
    fun isLocalNetwork(): Boolean = host.startsWith("192.168.") || host == "localhost"
}