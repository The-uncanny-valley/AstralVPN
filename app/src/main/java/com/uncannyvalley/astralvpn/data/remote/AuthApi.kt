package com.uncannyvalley.astralvpn.data.remote

import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
    @POST("auth/register")
    suspend fun register(
        @Query("email") email: String,
        @Query("name") name: String,
        @Query("password") password: String
    )
}