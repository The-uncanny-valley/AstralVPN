package com.uncannyvalley.astralvpn.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.uncannyvalley.astralvpn.data.local.PremiumLocalDataSource
import com.uncannyvalley.astralvpn.data.remote.PremiumRemoteDataSource
import com.uncannyvalley.astralvpn.domain.repository.PremiumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PremiumRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val local: PremiumLocalDataSource,
    private val remote: PremiumRemoteDataSource
) : PremiumRepository {

    override val isPremium: Flow<Boolean> =
        local.isPremium

    override suspend fun refresh() {
        val uid = firebaseAuth.currentUser?.uid ?: return
        val premium = remote.fetchPremium(uid)
        local.savePremium(premium)
    }
}