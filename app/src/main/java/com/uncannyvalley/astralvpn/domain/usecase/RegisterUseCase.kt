package com.uncannyvalley.astralvpn.domain.usecase

import com.uncannyvalley.astralvpn.domain.repository.AuthRepository

class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        name: String,
        password: String
    ): Result<Unit> {
        return repository.register(email, name, password)
    }
}