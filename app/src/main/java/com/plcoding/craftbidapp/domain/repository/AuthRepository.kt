package com.plcoding.craftbidapp.domain.repository

import com.plcoding.craftbidapp.data.LoggedInUser
import com.plcoding.craftbidapp.data.ResponseResult

interface AuthRepository {
    suspend fun login(email: String, password: String): ResponseResult<LoggedInUser>

    fun getToken(): String?

    fun saveToken(token: String)
}