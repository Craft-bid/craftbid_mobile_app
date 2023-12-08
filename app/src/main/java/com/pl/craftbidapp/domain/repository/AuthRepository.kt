package com.pl.craftbidapp.domain.repository

import com.pl.craftbidapp.data.LoggedInUser
import com.pl.craftbidapp.data.ResponseResult

interface AuthRepository {
    suspend fun login(email: String, password: String): ResponseResult<LoggedInUser>

    suspend fun register(name: String, email: String, password: String): ResponseResult<LoggedInUser>

    fun getToken(): String?

    fun saveToken(token: String)

    fun logout()
}