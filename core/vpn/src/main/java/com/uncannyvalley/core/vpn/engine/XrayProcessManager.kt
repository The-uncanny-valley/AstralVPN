package com.uncannyvalley.core.vpn.engine

import com.uncannyvalley.core.vpn.model.VpnStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File
import java.time.Instant

class XrayProcessManager {

    private var process: Process? = null
    private val _status = MutableStateFlow<VpnStatus>(VpnStatus.DISCONNECTED)
    val status: StateFlow<VpnStatus> = _status

    fun start(binary: File, config: File) {
        if (process != null) return

        try {
            _status.value = VpnStatus.CONNECTING
            
            process = ProcessBuilder()
                .command(
                    binary.absolutePath,
                    "-config",
                    config.absolutePath
                )
                .redirectErrorStream(true)
                .start()

            _status.value = VpnStatus.DISCONNECTED // temporary
            
        } catch (e: Exception) {
            // _status.value = VpnStatus.ERROR
            stop()
        }
    }

    fun stop() {
        process?.destroy()
        process = null
        _status.value = VpnStatus.DISCONNECTED
    }
    
    fun status(): StateFlow<VpnStatus> = status
}
