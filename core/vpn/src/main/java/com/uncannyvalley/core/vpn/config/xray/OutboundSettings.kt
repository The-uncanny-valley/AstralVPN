package com.uncannyvalley.core.vpn.config.xray

import kotlinx.serialization.Serializable

@Serializable
data class OutboundSettings(
    val vnext: List<VNext>
)

@Serializable
data class VNext(
    val address: String,
    val port: Int,
    val users: List<User>
)

@Serializable
data class User(
    val id: String,
    val encryption: String,
    val flow: String? = null
)