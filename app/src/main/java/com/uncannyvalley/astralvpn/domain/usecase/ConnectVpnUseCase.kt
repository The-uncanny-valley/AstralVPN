package com.uncannyvalley.astralvpn.domain.usecase

import com.uncannyvalley.astralvpn.domain.model.VpnConfig
import com.uncannyvalley.astralvpn.domain.repository.VpnRepository

class ConnectVpnUseCase(private val vpnRepository: VpnRepository) {
    suspend operator fun invoke(config: VpnConfig) = vpnRepository.connect(config)
}

class DisconnectVpnUseCase(private val vpnRepository: VpnRepository) {
    suspend operator fun invoke() = vpnRepository.disconnect()
}