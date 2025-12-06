package com.uncannyvalley.astralvpn.di

import android.content.Context
import androidx.room.Room
import com.uncannyvalley.astralvpn.data.dao.VpnConfigDao
import com.uncannyvalley.astralvpn.data.db.AstralVpnDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AstralVpnDatabase {
        return Room.databaseBuilder(
            context,
            AstralVpnDatabase::class.java,
            "astral_db"
        ).build()
    }

    @Provides
    fun provideVpnConfigDao(
        database: AstralVpnDatabase
    ): VpnConfigDao = database.vpnConfigDao()
}