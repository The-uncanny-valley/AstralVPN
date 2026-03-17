package com.uncannyvalley.core.vpn.config.xray

import kotlinx.serialization.Serializable

@Serializable
data class Inbound(
    val listen: String,
    val port: Int,
    val protocol: String
)