package com.uncannyvalley.core.vpn.engine

import com.uncannyvalley.astral.core.domain.model.VpnConfig
import com.uncannyvalley.astral.core.domain.model.VpnStatus
import kotlinx.coroutines.flow.Flow

interface VpnEngine {
    suspend fun start(config: VpnConfig)
    suspend fun stop()

    fun status(): Flow<VpnStatus>
}