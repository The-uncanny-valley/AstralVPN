package com.uncannyvalley.astralvpn.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.uncannyvalley.astralvpn.data.local.PremiumDataStore
import com.uncannyvalley.astralvpn.data.local.PremiumLocalDataSource
import com.uncannyvalley.astralvpn.data.local.premiumDataStore
import com.uncannyvalley.astralvpn.data.remote.PremiumRemoteDataSource
import com.uncannyvalley.astralvpn.data.repository.PremiumRepositoryImpl
import com.uncannyvalley.astralvpn.domain.repository.PremiumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PremiumModule {

    @Provides
    @Singleton
    fun providePremiumDataStore(context: Context): PremiumLocalDataSource {
        return PremiumDataStore(
            dataStore = context.premiumDataStore
        )
    }

    @Provides
    @Singleton
    fun providePremiumRepository(
        dataSource: PremiumLocalDataSource,
        remoteDataSource: PremiumRemoteDataSource
    ): PremiumRepository {
        return PremiumRepositoryImpl(
            local = dataSource,
            remote = remoteDataSource,
            firebaseAuth = FirebaseAuth.getInstance()
        )
    }
}