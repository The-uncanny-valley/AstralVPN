package com.uncannyvalley.astralvpn.data.mapper

import com.uncannyvalley.astralvpn.data.model.SplitTunnelingConfigEntity
import com.uncannyvalley.astralvpn.data.model.VpnConfigEntity
import com.uncannyvalley.astralvpn.domain.model.AuthData
import com.uncannyvalley.astralvpn.domain.model.ConfigId
import com.uncannyvalley.astralvpn.domain.model.Protocol
import com.uncannyvalley.astralvpn.domain.model.ServerEndpoint
import com.uncannyvalley.astralvpn.domain.model.SplitTunnelingConfig
import com.uncannyvalley.astralvpn.domain.model.VpnConfig

fun SplitTunnelingConfigEntity.toDomain(): SplitTunnelingConfig {
    val apps = if (excludedApps.isNotBlank()) {
        excludedApps.split(",").toSet()
    } else {
        emptySet()
    }
    return SplitTunnelingConfig(excludedApps = apps)
}

fun SplitTunnelingConfig.toEntity(): SplitTunnelingConfigEntity {
    val apps = excludedApps.joinToString(",")
    return SplitTunnelingConfigEntity(excludedApps = apps)
}

fun VpnConfigEntity.toDomain(): VpnConfig {
    val endpoint = ServerEndpoint(
        host = host,
        port = port
    )

    val auth = when (authType) {
        "vless" -> uuid?.let { AuthData.VlessAuth(it) }
        "password" -> if (!username.isNullOrBlank() && !password.isNullOrBlank()) {
            AuthData.UsernamePassword(username, password)
        } else null

        else -> null
    }

    val splitTunneling = SplitTunnelingConfig(
        excludedApps = excludedApps
    )

    return VpnConfig(
        id = ConfigId(id),
        name = name,
        endpoint = endpoint,
        protocol = Protocol.valueOf(protocol),
        auth = auth,
        splitTunneling = splitTunneling,
        isActive = isActive
    )
}

fun VpnConfig.toEntity(): VpnConfigEntity {

    data class AuthValues(
        val authType: String? = null,
        val uuid: String? = null,
        val username: String? = null,
        val password: String? = null
    )

    val authValues = when (val auth = auth) {
        is AuthData.VlessAuth -> AuthValues(
            authType = "vless",
            uuid = auth.uuid
        )
        is AuthData.UsernamePassword -> AuthValues(
            authType = "password",
            username = auth.username,
            password = auth.password
        )
        else -> AuthValues()
    }

    return VpnConfigEntity(
        id = id.value,
        name = name,
        host = endpoint.host,
        port = endpoint.port,
        protocol = protocol.name,
        authType = authValues.authType,
        uuid = authValues.uuid,
        username = authValues.username,
        password = authValues.password,
        excludedApps = splitTunneling.excludedApps,
        isActive = isActive
    )
}