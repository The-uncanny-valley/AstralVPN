package com.uncannyvalley.astralvpn.data.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.uncannyvalley.astralvpn.domain.ConnectivityObserver
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class NetworkConnectivityObserver @Inject constructor(
    @ApplicationContext
    private val context: Context
) : ConnectivityObserver {

    override fun observe(): Flow<ConnectivityObserver.Status> = callbackFlow {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(ConnectivityObserver.Status.Available)
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                trySend(ConnectivityObserver.Status.Losing)
            }

            override fun onLost(network: Network) {
                trySend(ConnectivityObserver.Status.Lost)
            }

            override fun onUnavailable() {
                trySend(ConnectivityObserver.Status.Unavailable)
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        cm.registerNetworkCallback(request, callback)

        awaitClose {
            cm.unregisterNetworkCallback(callback)
        }
    }

    override suspend fun isOnline(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        val activeNetwork = cm.activeNetwork ?: return false

        val capabilities = cm.getNetworkCapabilities(activeNetwork) ?: return false

        val hasInternet = capabilities
            .hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

        val isValidated = capabilities
            .hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)

        return hasInternet && isValidated
    }
}