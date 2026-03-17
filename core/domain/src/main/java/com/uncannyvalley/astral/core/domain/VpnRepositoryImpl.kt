package com.uncannyvalley.astral.core.domain

import com.uncannyvalley.astral.core.domain.model.VpnStatus
import com.uncannyvalley.astralvpn.data.dao.VpnConfigDao
import com.uncannyvalley.astralvpn.domain.model.ConfigId
import com.uncannyvalley.astralvpn.domain.repository.VpnRepository
import com.uncannyvalley.core.vpn.engine.VpnEngineFactory
import kotlinx.coroutines.flow.Flow

class VpnRepositoryImpl(
    private val vpnConfigDao: VpnConfigDao,
    private val engineFactory: VpnEngineFactory
) : VpnRepository {

    override suspend fun connect(config: com.uncannyvalley.astralvpn.domain.model.VpnConfig): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun disconnect(): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getStatus(): Flow<VpnStatus> {
        TODO("Not yet implemented") // return a flow from a service layer
    }

    override suspend fun updateExcludedApps(
        configId: ConfigId,
        excludedApps: Set<String>
    ): Result<Unit> {
        TODO("Not yet implemented")
    }
}