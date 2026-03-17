package com.uncannyvalley.core.vpn.engine

class VpnEngineFactory(
    private val xrayEngine: XrayEngine
) {

    fun create(protocol: String): VpnEngine {

        return when (protocol.lowercase()) {
            "vless", "vmess" -> xrayEngine
            else -> throw IllegalArgumentException("Unsupported protocol: $protocol")
        }
    }
}