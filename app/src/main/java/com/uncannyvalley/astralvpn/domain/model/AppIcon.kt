package com.uncannyvalley.astralvpn.domain.model

interface AppIconManager {
    fun setIcon(icon: AppIcon)
}

enum class AppIcon {
    DEFAULT, LIGHT, CALC, CLOCK
}