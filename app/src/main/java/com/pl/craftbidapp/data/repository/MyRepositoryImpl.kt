package com.pl.craftbidapp.data.repository

import android.app.Application
import android.security.keystore.UserNotAuthenticatedException
import com.pl.craftbidapp.R
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.data.remote.HelloWorldResponse
import com.pl.craftbidapp.data.remote.MyApi
import com.pl.craftbidapp.domain.repository.MyRepository
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

    override suspend fun helloWorld(): ResponseResult<HelloWorldResponse> {
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