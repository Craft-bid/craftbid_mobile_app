package com.plcoding.daggerhiltcourse.data.remote

import com.plcoding.daggerhiltcourse.data.LoggedInUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/v1/auth/authenticate")
    suspend fun authenticate(@Body loginRequest: LoginRequest): Response<LoggedInUser>
}

data class LoginRequest(val email: String, val password: String)