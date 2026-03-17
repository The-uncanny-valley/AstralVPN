package com.uncannyvalley.core.vpn.engine

import com.uncannyvalley.core.vpn.model.VpnConfig
import com.uncannyvalley.core.vpn.model.VpnStatus
import kotlinx.coroutines.flow.Flow

interface VpnEngine {
    suspend fun start(config: VpnConfig)
    suspend fun stop()

    fun status(): Flow<VpnStatus>
}