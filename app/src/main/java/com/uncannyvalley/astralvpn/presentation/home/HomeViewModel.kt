package com.uncannyvalley.astralvpn.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uncannyvalley.astralvpn.data.connectivity.NetworkConnectivityObserver
import com.uncannyvalley.astralvpn.data.dao.VpnConfigDao
import com.uncannyvalley.astralvpn.data.mapper.toDomain
import com.uncannyvalley.astralvpn.domain.ConnectivityObserver
import com.uncannyvalley.astralvpn.domain.model.AuthData
import com.uncannyvalley.astralvpn.domain.model.ConfigId
import com.uncannyvalley.astralvpn.domain.model.Protocol
import com.uncannyvalley.astralvpn.domain.model.ServerEndpoint
import com.uncannyvalley.astralvpn.domain.model.VpnConfig
import com.uncannyvalley.astralvpn.domain.usecase.ConnectVpnUseCase
import com.uncannyvalley.astralvpn.domain.usecase.DisconnectVpnUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val connectVpnUseCase: ConnectVpnUseCase,
    private val disconnectVpnUseCase: DisconnectVpnUseCase,
    private val vpnConfigDao: VpnConfigDao,
    private val connectivity: ConnectivityObserver
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Normal)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        observeConnectivity()
    }

    private fun observeConnectivity() {
        viewModelScope.launch {
            connectivity.observe().collect { status ->
                when (status) {
                    ConnectivityObserver.Status.Available -> {
                        if (uiState.value is HomeUiState.NoInternet) {
                            _uiState.value = HomeUiState.Normal
                        }
                    }

                    ConnectivityObserver.Status.Unavailable,
                    ConnectivityObserver.Status.Lost,
                    ConnectivityObserver.Status.Losing -> {
                        _uiState.value = HomeUiState.NoInternet
                    }
                }
            }
        }
    }

    fun onConnectClicked() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Connecting

            delay(5000)

            _uiState.value = HomeUiState.Connected
//            if (!connectivity.isOnline()) {
//                _uiState.value = HomeUiState.NoInternet
//                return@launch
//            }
//
//            _uiState.value = HomeUiState.Connecting
//
//            val entity = vpnConfigDao.getById("default")
//            val config = entity?.toDomain() ?: return@launch run {
//                _uiState.value = HomeUiState.Error("Missing VPN config")
//            }
//
//            connectVpnUseCase(config)
//                .onSuccess { _uiState.value = HomeUiState.Connected }
//                .onFailure { _uiState.value = HomeUiState.Error(it.message ?: "Failed") }
        }
    }

    fun onDisconnectClicked() {
        viewModelScope.launch {
            disconnectVpnUseCase()
            _uiState.value = HomeUiState.Normal
        }
    }

    fun refreshConnectionStatus() {
        viewModelScope.launch {
            if (connectivity.isOnline()) {
                _uiState.value = HomeUiState.Normal
            } else {
                _uiState.value = HomeUiState.NoInternet
            }
        }
    }
}

private val defaultConfig = VpnConfig(
    id = ConfigId("default"),
    name = "AstralVPN Default",
    endpoint = ServerEndpoint(host = "example.com", port = 443),
    protocol = Protocol.VLESS,
    auth = AuthData.VlessAuth(uuid = "YOUR-UUID-HERE"),
    isActive = false
)