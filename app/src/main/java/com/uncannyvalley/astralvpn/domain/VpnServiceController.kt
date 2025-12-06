package com.uncannyvalley.astralvpn.domain

import com.uncannyvalley.astralvpn.domain.model.VpnConfig

interface VpnServiceController {
    suspend fun start(config: VpnConfig): Result<Unit>
    suspend fun stop(): Result<Unit>
}