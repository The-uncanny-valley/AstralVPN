package com.uncannyvalley.astralvpn.domain.repository

import com.uncannyvalley.astralvpn.domain.model.VpnConfig
import com.uncannyvalley.astralvpn.domain.model.VpnStatus

interface VpnRepository {
    suspend fun connect(config: VpnConfig): Result<Unit>
    suspend fun disconnect(): Result<Unit>
    suspend fun getStatus(): VpnStatus
}