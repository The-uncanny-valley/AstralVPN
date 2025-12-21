package com.uncannyvalley.astralvpn.domain.usecase

import com.uncannyvalley.astralvpn.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> {
        return repository.login(email, password)
    }
}