package com.uncannyvalley.astralvpn.data.dao

import androidx.room.*
import com.uncannyvalley.astralvpn.data.model.VpnConfigEntity

@Dao
interface VpnConfigDao {
    @Query("SELECT * FROM vpn_config")
    suspend fun getAll(): List<VpnConfigEntity>

    @Query("SELECT * FROM vpn_config WHERE id = :id")
    suspend fun getById(id: String): VpnConfigEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(config: VpnConfigEntity)

    @Delete
    suspend fun delete(config: VpnConfigEntity)
}