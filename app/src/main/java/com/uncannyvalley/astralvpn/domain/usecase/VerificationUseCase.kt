package com.uncannyvalley.astralvpn.domain.usecase

import com.uncannyvalley.astralvpn.domain.repository.AuthRepository

class VerificationUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(code: String): Result<Unit> {
        return if (code == "1234") {
            Result.success(Unit)
        } else {
            Result.failure(Exception("Invalid"))
        }
    }
}
