package com.uncannyvalley.astralvpn.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vpn_config")
data class VpnConfigEntity(
    @PrimaryKey val id: String,
    val name: String,
    val host: String,
    val port: Int,
    val protocol: String,
    val authType: String?,
    val uuid: String?,
    val username: String?,
    val password: String?,
    val excludedApps: String,
    val isActive: Boolean
)
