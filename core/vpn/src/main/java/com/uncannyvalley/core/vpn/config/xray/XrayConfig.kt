package com.uncannyvalley.core.vpn.config.xray

import kotlinx.serialization.Serializable

@Serializable
data class XrayConfig(
    val log: LogConfig,
    val routing: RoutingConfig,
    val inbounds: List<Inbound>,
    val outbounds: List<Outbound>
)