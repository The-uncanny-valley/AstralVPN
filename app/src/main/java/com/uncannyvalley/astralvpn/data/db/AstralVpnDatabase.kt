package com.uncannyvalley.astralvpn.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uncannyvalley.astralvpn.data.dao.VpnConfigDao
import com.uncannyvalley.astralvpn.data.model.VpnConfigEntity

@Database(
    entities = [VpnConfigEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AstralVpnDatabase: RoomDatabase() {
    abstract fun vpnConfigDao(): VpnConfigDao
}
