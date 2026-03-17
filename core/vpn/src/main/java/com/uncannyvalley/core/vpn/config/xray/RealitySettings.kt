package com.uncannyvalley.core.vpn.config.xray

import kotlinx.serialization.Serializable

@Serializable
data class RealitySettings(
    val fingerprint: String,
    val serverName: String,
    val password: String,
    val shortId: String
)