package com.uncannyvalley.core.vpn.engine

import com.uncannyvalley.core.vpn.config.xray.XrayConfigManager
import com.uncannyvalley.core.vpn.model.VpnConfig
import com.uncannyvalley.core.vpn.model.VpnStatus
import kotlinx.coroutines.flow.Flow

class XrayEngine(
    private val binaryManager: XrayBinaryManager,
    private val configManager: XrayConfigManager,
    private val processManager: XrayProcessManager
) : VpnEngine {
    override suspend fun start(config: VpnConfig) {


        val binary = binaryManager.prepareBinary()

        val configFile = configManager.createConfig(config)

        processManager.start(binary, configFile)
    }

    override suspend fun stop() {
        processManager.stop()
    }

    override fun status(): Flow<VpnStatus> {
        TODO("Not yet implemented")
    }
}