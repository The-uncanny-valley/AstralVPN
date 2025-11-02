package com.uncannyvalley.astralvpn.data.model

data class VpnConfigEntity(
    val id: String,
    val name: String,
    val host: String,
    val port: Int,
    val protocol: String,
    val authType: String?,
    val uuid: String?,
    val username: String?,
    val password: String?,
    val excludedApps: Set<String>,
    val isActive: Boolean
)
