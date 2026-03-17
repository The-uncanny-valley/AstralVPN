package com.uncannyvalley.core.vpn.config.xray

import kotlinx.serialization.Serializable

@Serializable
data class RoutingConfig(
    val rules: List<RoutingRule>
)

@Serializable
data class RoutingRule(
    val ip: List<String>,
    val outboundTag: String
)
