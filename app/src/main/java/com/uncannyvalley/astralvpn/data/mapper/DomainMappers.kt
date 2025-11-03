package com.uncannyvalley.astralvpn.data.mapper

import com.uncannyvalley.astralvpn.data.model.SplitTunnelingConfigEntity
import com.uncannyvalley.astralvpn.domain.model.SplitTunnelingConfig

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