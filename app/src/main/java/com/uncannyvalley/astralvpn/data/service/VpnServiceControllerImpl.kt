package com.uncannyvalley.astralvpn.data.service

import android.content.Context
import android.content.Intent
import android.net.VpnService
import com.uncannyvalley.astralvpn.domain.VpnServiceController
import com.uncannyvalley.astralvpn.domain.model.VpnConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class VpnServiceControllerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : VpnServiceController {

    override suspend fun start(config: VpnConfig): Result<Unit> {
        return try {
            val prepare = VpnService.prepare(context)
            if (prepare != null) {
                return Result.failure(
                    IllegalStateException("VPN permission not granted")
                )
            }

            val intent = Intent(context, AstralVpnService::class.java)
            intent.putExtra("config_id", config.id.value)
            context.startService(intent)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun stop(): Result<Unit> {
        return try {
            context.stopService(Intent(context, AstralVpnService::class.java))
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}