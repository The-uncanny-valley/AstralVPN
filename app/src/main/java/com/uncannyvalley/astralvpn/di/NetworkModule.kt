package com.uncannyvalley.astralvpn.di

import android.content.Context
import com.uncannyvalley.astralvpn.data.connectivity.NetworkConnectivityObserver
import com.uncannyvalley.astralvpn.domain.ConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideConnectivityObserver(
        @ApplicationContext context: Context
    ): ConnectivityObserver = NetworkConnectivityObserver(context)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https:your.api.url/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}