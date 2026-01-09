package com.uncannyvalley.astralvpn.data.local

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private const val PREMIUM_DATA_STORE_NAME = "premium_prefs"

val Context.premiumDataStore by preferencesDataStore(
    name = PREMIUM_DATA_STORE_NAME
)