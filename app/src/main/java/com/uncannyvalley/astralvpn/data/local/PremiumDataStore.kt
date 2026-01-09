package com.uncannyvalley.astralvpn.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences

class PremiumDataStore(
    private val dataStore: DataStore<Preferences>
) : PremiumLocalDataSource {

    private companion object {
        val PREMIUM_KEY = booleanPreferencesKey("is_premium")
    }

    override val isPremium: Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[PREMIUM_KEY] ?: false
        }

    override suspend fun savePremium(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[PREMIUM_KEY] = value
        }
    }

    override suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.remove(PREMIUM_KEY)
        }
    }

}
