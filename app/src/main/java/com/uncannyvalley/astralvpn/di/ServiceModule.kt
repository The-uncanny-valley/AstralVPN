package com.uncannyvalley.astralvpn.di

import android.content.Context
import com.uncannyvalley.astralvpn.domain.VpnServiceController
import com.uncannyvalley.astralvpn.data.service.VpnServiceControllerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideVpnServiceController(
        @ApplicationContext context: Context
    ): VpnServiceController = VpnServiceControllerImpl(context)
}