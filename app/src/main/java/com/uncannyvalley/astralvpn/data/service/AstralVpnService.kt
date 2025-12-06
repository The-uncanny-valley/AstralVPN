package com.uncannyvalley.astralvpn.data.service

import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AstralVpnService : VpnService() {
    private var vpnInterface: ParcelFileDescriptor? = null

    companion object {
        const val VPN_MTU = 1500
        const val VPN_ADDRESS = "10.0.0.2"
        const val VPN_ROUTE = "0.0.0.0"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startVpn()
        return START_STICKY
    }

    private fun startVpn() {
        if (vpnInterface != null) return

        val builder = Builder()
        builder.setMtu(VPN_MTU)
        builder.setSession("AstralVPN")
        builder.addRoute(VPN_ROUTE, 0)
        builder.addAddress(VPN_ADDRESS, 24)

        builder.addDnsServer("8.8.8.8")
        builder.addDnsServer("8.8.4.4")

        vpnInterface = builder.establish()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopVpn()
    }

    private fun stopVpn() {
        vpnInterface?.close()
        vpnInterface = null
    }
}