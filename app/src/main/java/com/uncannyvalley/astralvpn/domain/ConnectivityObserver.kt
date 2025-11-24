package com.uncannyvalley.astralvpn.domain

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    enum class Status{
        Available,
        Unavailable,
        Losing,
        Lost
    }

    fun observe(): Flow<Status>
    suspend fun isOnline(): Boolean
}