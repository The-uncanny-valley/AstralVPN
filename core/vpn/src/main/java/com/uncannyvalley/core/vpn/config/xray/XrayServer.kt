package com.uncannyvalley.core.vpn.config.xray

data class XrayServer(
    val address: String,
    val port: Int,
    val uuid: String,
    val protocol: String
)