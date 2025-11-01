package com.uncannyvalley.astralvpn.domain.model

data class SplitTunnelingConfig(
    val excludedApps: Set<String> = emptySet()
) {
    fun isAppRoutedThroughVpn(packageName: String): Boolean {
        return !excludedApps.contains(packageName)
    }
}