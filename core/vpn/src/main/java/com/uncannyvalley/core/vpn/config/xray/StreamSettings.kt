package com.uncannyvalley.core.vpn.config.xray

import kotlinx.serialization.Serializable

@Serializable
data class StreamSettings(
    val network: String,
    val security: String,
    val realitySettings: RealitySettings? = null
)