package com.uncannyvalley.core.vpn.model

data class VpnConfig(
    val id: String,
    val name: String,
    val host: String,
    val port: Int,
    val protocol: String,
    val authType: String? = null,
    val uuid: String? = null,
    val username: String? = null,
    val password: String? = null,
    val excludedApps: List<String> = emptyList()
)
