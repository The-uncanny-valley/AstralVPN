package com.uncannyvalley.astralvpn.domain.repository

import com.uncannyvalley.astralvpn.domain.model.AppIcon
import kotlinx.coroutines.flow.Flow

interface AppIconRepository {
    suspend fun saveIcon(icon: AppIcon)
    fun observeIcon(): Flow<AppIcon>
}