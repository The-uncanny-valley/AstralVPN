package com.uncannyvalley.astralvpn.di

import com.uncannyvalley.astralvpn.VpnServiceController
import com.uncannyvalley.astralvpn.data.dao.VpnConfigDao
import com.uncannyvalley.astralvpn.data.repository.VpnRepositoryImpl
import com.uncannyvalley.astralvpn.domain.repository.VpnRepository
import com.uncannyvalley.astralvpn.domain.usecase.ConnectVpnUseCase
import com.uncannyvalley.astralvpn.domain.usecase.DisconnectVpnUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideVpnRepository(
        vpnConfigDao: VpnConfigDao,
        vpnServiceController: VpnServiceController
    ): VpnRepository {
        return VpnRepositoryImpl(
            vpnConfigDao = vpnConfigDao
        )
    }

    @Provides
    @Singleton
    fun provideConnectVpnUseCase(
        repository: VpnRepository
    ): ConnectVpnUseCase = ConnectVpnUseCase(repository)

    @Provides
    @Singleton
    fun provideDisconnectVpnUseCase(
        repository: VpnRepository
    ): DisconnectVpnUseCase = DisconnectVpnUseCase(repository)
}