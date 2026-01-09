package com.uncannyvalley.astralvpn.data.remote

interface PremiumRemoteDataSource {
    suspend fun fetchPremium(uid: String): Boolean
}