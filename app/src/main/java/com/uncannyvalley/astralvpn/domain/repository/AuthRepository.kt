package com.uncannyvalley.astralvpn.domain.repository

interface AuthRepository {
    suspend fun register(
        email: String,
        name: String,
        password: String
    ): Result<Unit>

    suspend fun login(
        email: String,
        password: String
    ): Result<Unit>
}