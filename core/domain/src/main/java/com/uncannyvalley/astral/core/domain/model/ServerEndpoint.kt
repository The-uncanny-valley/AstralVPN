package com.uncannyvalley.astral.core.domain.model

data class ServerEndpoint(
    val host: String,
    val port: Int,
    val protocol: Protocol,
    val metadata: Map<String, String> = emptyMap() // other parameters
)