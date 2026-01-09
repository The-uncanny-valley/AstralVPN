package com.uncannyvalley.astralvpn.domain.repository

import kotlinx.coroutines.flow.Flow

interface PremiumRepository {
    val isPremium: Flow<Boolean>
    suspend fun refresh()
}