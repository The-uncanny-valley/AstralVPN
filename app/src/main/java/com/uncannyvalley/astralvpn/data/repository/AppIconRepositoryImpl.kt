package com.uncannyvalley.astralvpn.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.uncannyvalley.astralvpn.domain.model.AppIcon
import com.uncannyvalley.astralvpn.domain.repository.AppIconRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppIconRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AppIconRepository {

    companion object {
        val ICON_KEY = stringPreferencesKey("app_icon")
    }

    override suspend fun saveIcon(icon: AppIcon) {
        dataStore.edit {
            it[ICON_KEY] = icon.name
        }
    }

    override fun observeIcon(): Flow<AppIcon> =
        dataStore.data.map { prefs ->
            prefs[ICON_KEY]?.let {
                runCatching { AppIcon.valueOf(it) }.getOrNull()
            } ?: AppIcon.DEFAULT
        }
}