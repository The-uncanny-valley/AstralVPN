package com.uncannyvalley.core.vpn.config.xray

import kotlinx.serialization.Serializable

@Serializable
data class LogConfig(
    val loglevel: String
)