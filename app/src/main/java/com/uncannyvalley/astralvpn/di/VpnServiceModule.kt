package com.uncannyvalley.astralvpn.di

import android.content.Context
import android.content.Intent
import com.uncannyvalley.astralvpn.data.service.AstralVpnService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VpnServiceModule {

    @Provides
    @Singleton
    fun provideAstralVpnService(
        @ApplicationContext context: Context
    ): AstralVpnService {
        val intent = Intent(context, AstralVpnService::class.java)
        context.startService(intent)
        return context.getSystemService(
            AstralVpnService::class.java
        ) as AstralVpnService
    }
}