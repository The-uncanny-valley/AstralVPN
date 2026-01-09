package com.uncannyvalley.astralvpn.data.local

import kotlinx.coroutines.flow.Flow

interface PremiumLocalDataSource {
    val isPremium: Flow<Boolean>
    suspend fun savePremium(value: Boolean)
    suspend fun clear()
}