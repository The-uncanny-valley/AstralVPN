package com.uncannyvalley.astralvpn.di

import com.uncannyvalley.astralvpn.data.AppIconManagerImpl
import com.uncannyvalley.astralvpn.data.repository.AppIconRepositoryImpl
import com.uncannyvalley.astralvpn.domain.model.AppIconManager
import com.uncannyvalley.astralvpn.domain.repository.AppIconRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppIconModule {

    @Binds
    @Singleton
    abstract fun bindAppIconManager(
        impl: AppIconManagerImpl
    ): AppIconManager

    @Binds
    @Singleton
    abstract fun bindAppIconRepository(
        impl: AppIconRepositoryImpl
    ): AppIconRepository
}