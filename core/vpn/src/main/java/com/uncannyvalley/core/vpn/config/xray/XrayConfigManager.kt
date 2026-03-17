package com.uncannyvalley.core.vpn.config.xray

import android.content.Context
import com.uncannyvalley.core.vpn.model.VpnConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.File

class XrayConfigManager(
    private val context: Context,
    private val json: Json
) {

    suspend fun createConfig(config: VpnConfig): File = withContext(Dispatchers.IO) {
        val xrayConfig = buildRealityConfig(config)

        val configFile = File(context.filesDir, "xray_config.json")

        configFile.writeText(
            json.encodeToString(xrayConfig)
        )

        configFile
    }

    private fun buildRealityConfig(config: VpnConfig): XrayConfig {
        return XrayConfig(
            log = LogConfig(
                loglevel = "warning"
            ),

            routing = RoutingConfig(
                rules = listOf(
                    RoutingRule(
                        ip = listOf("geoip:private"),
                        outboundTag = "direct"
                    )
                )
            ),

            inbounds = listOf(
                Inbound(
                    listen = "127.0.0.1",
                    port = 65000,
                    protocol = "vless"
                )
            ),

            outbounds = listOf(
                Outbound(
                    protocol = "vless",

                    settings = OutboundSettings(
                        vnext = listOf(
                            VNext(
                                address = config.host,
                                port = config.port,
                                users = listOf(
                                    User(
                                        id = config.uuid ?: "",
                                        encryption = "none",
                                        flow = "xtls-rprx-vision"
                                    )
                                )
                            )
                        )
                    ),

                    streamSettings = StreamSettings(
                        network = "raw",
                        security = "reality",

                        realitySettings = RealitySettings(
                            fingerprint = "chrome",
                            serverName = "debian.org",
                            password = config.password ?: "",
                            shortId = "a721e88cae0b"
                        )
                    ),
                    tag = "proxy"
                ),

                Outbound(
                    protocol = "freedom",
                    tag = "direct"
                )
            )
        )
    }
}
