package com.plcoding.daggerhiltcourse.data.repository

import android.app.Application
import android.security.keystore.UserNotAuthenticatedException
import com.plcoding.daggerhiltcourse.R
import com.plcoding.daggerhiltcourse.data.ResponseResult
import com.plcoding.daggerhiltcourse.data.remote.HelloWorldResponse
import com.plcoding.daggerhiltcourse.data.remote.MyApi
import com.plcoding.daggerhiltcourse.domain.repository.MyRepository
import java.io.IOException
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val api: MyApi,
    private val appContext: Application
): MyRepository {

    init {
        val appName = appContext.getString(R.string.app_name)
        println("Hello from the repository. The app name is $appName")
    }

    override suspend fun doNetworkCall(): ResponseResult<HelloWorldResponse> {
        return try {
            val response = api.helloWorld()

            if (response.isSuccessful && response.body() != null) {
                ResponseResult.Success(response.body()!!)
            } else {
                ResponseResult.Error(UserNotAuthenticatedException())
            }
        } catch (e: Throwable) {
            ResponseResult.Error(IOException("Error logging in", e))
        }
    }
}