package com.uncannyvalley.astralvpn.domain.usecase

import com.uncannyvalley.astralvpn.domain.model.AppIcon
import com.uncannyvalley.astralvpn.domain.model.AppIconManager
import javax.inject.Inject

class ChangeAppIconUseCase @Inject constructor(
    private val appIconManager: AppIconManager
) {
    operator fun invoke(icon: AppIcon) {
        appIconManager.setIcon(icon)
    }
}