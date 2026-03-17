package com.uncannyvalley.core.vpn.config.xray

import kotlinx.serialization.Serializable

@Serializable
data class Outbound(
    val protocol: String,
    val settings: OutboundSettings? = null,
    val streamSettings: StreamSettings? = null,
    val tag: String
)