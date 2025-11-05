package com.uncannyvalley.astralvpn.data.repository

import com.uncannyvalley.astralvpn.data.dao.VpnConfigDao
import com.uncannyvalley.astralvpn.domain.model.ConfigId
import com.uncannyvalley.astralvpn.domain.model.VpnConfig
import com.uncannyvalley.astralvpn.domain.model.VpnStatus
import com.uncannyvalley.astralvpn.domain.repository.VpnRepository
import kotlinx.coroutines.flow.Flow

class VpnRepositoryImpl(
    private val dao: VpnConfigDao
) : VpnRepository {

    override suspend fun connect(config: VpnConfig): Result<Unit> {
        TODO("Not yet implemented") // // use Xray launcher later
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