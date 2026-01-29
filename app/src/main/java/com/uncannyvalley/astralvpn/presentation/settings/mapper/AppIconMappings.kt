package com.uncannyvalley.astralvpn.presentation.settings.mapper

import com.uncannyvalley.astralvpn.R
import com.uncannyvalley.astralvpn.domain.model.AppIcon
import com.uncannyvalley.astralvpn.presentation.icon.AppIconOption

fun AppIcon.toUiModel(): AppIconOption {
    return AppIconOption(
        id = name,
        imageRes = when (this) {
            AppIcon.DEFAULT -> R.drawable.ic_app_default
            AppIcon.LIGHT -> R.drawable.ic_app_light
            AppIcon.CALC -> R.drawable.ic_app_calc
            AppIcon.CLOCK -> R.drawable.ic_app_clock
        }
    )
}