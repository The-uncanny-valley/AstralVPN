package com.uncannyvalley.astralvpn.domain.repository

import com.uncannyvalley.astral.core.domain.model.VpnStatus
import com.uncannyvalley.astralvpn.domain.model.ConfigId
import com.uncannyvalley.astralvpn.domain.model.VpnConfig
import kotlinx.coroutines.flow.Flow

interface VpnRepository {
    suspend fun connect(config: VpnConfig): Result<Unit>
    suspend fun disconnect(): Result<Unit>
    suspend fun getStatus(): Flow<VpnStatus>
    suspend fun updateExcludedApps(configId: ConfigId, excludedApps: Set<String>): Result<Unit>
}