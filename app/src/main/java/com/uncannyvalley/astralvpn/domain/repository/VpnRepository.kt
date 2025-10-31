package com.uncannyvalley.astralvpn.domain.repository

import com.uncannyvalley.astralvpn.domain.model.VpnConfig
import com.uncannyvalley.astralvpn.domain.model.VpnStatus
import kotlinx.coroutines.flow.Flow

interface VpnRepository {
    suspend fun connect(config: VpnConfig): Result<Unit>
    suspend fun disconnect(): Result<Unit>
    suspend fun getStatus(): Flow<VpnStatus>
}