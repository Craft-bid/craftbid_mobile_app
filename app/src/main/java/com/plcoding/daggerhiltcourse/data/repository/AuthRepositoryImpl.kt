package com.plcoding.daggerhiltcourse.data.repository

import android.content.SharedPreferences
import android.security.keystore.UserNotAuthenticatedException
import com.plcoding.daggerhiltcourse.data.CRAFT_BID_JWT_TOKEN
import com.plcoding.daggerhiltcourse.data.LoggedInUser
import com.plcoding.daggerhiltcourse.data.ResponseResult
import com.plcoding.daggerhiltcourse.data.remote.AuthApi
import com.plcoding.daggerhiltcourse.data.remote.LoginRequest
import com.plcoding.daggerhiltcourse.domain.repository.AuthRepository
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val sharedPreferences: SharedPreferences
): AuthRepository {

    override suspend fun login(email: String, password: String): ResponseResult<LoggedInUser> {
        return try {
            val response = api.authenticate(LoginRequest(email, password))
            if (response.isSuccessful && response.body() != null) {
                ResponseResult.Success(response.body()!!)
            } else {
                ResponseResult.Error(UserNotAuthenticatedException())
            }
        } catch (e: Throwable) {
            ResponseResult.Error(IOException("Error logging in", e))
        }

    }

    override fun getToken(): String? {
        return sharedPreferences.getString(CRAFT_BID_JWT_TOKEN, null)
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(CRAFT_BID_JWT_TOKEN, token).apply()
    }
}