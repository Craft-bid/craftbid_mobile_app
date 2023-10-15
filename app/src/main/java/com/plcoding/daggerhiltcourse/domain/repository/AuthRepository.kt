package com.plcoding.daggerhiltcourse.domain.repository

import com.plcoding.daggerhiltcourse.data.LoggedInUser
import com.plcoding.daggerhiltcourse.data.ResponseResult

interface AuthRepository {
    suspend fun login(email: String, password: String): ResponseResult<LoggedInUser>

    fun getToken(): String?

    fun saveToken(token: String)
}