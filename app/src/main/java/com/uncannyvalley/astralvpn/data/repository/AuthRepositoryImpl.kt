package com.uncannyvalley.astralvpn.data.repository

import com.uncannyvalley.astralvpn.data.remote.AuthApi
import com.uncannyvalley.astralvpn.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
     private val api: AuthApi
) : AuthRepository {

    override suspend fun register(
        email: String,
        name: String,
        password: String
    ): Result<Unit> {
        return try {
            api.register(email, name, password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}