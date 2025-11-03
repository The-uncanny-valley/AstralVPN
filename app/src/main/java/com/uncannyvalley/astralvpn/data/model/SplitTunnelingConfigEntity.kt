package com.uncannyvalley.astralvpn.data.model

data class SplitTunnelingConfigEntity(
    val excludedApps: String = "" // stored as a comma-separated string for simplicity
)