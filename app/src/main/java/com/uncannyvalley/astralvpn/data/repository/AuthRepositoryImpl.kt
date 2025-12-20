package com.uncannyvalley.astralvpn.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.uncannyvalley.astralvpn.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
     private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun register(
        email: String,
        name: String,
        password: String
    ): Result<Unit> = try {
        firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .await()

        firebaseAuth.currentUser
            ?.sendEmailVerification()
            ?.await()

        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}