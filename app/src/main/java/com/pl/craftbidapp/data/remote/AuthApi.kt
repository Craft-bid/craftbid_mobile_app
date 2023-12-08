package com.pl.craftbidapp.data.remote

import com.pl.craftbidapp.data.LoggedInUser
import com.pl.craftbidapp.data.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/v1/auth/authenticate")
    suspend fun authenticate(@Body loginRequest: LoginRequest): Response<LoggedInUser>

    @POST("api/v1/auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<LoggedInUser>
}

data class LoginRequest(val email: String, val password: String)