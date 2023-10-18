package com.plcoding.craftbidapp.domain.repository

import com.plcoding.craftbidapp.data.ResponseResult
import com.plcoding.craftbidapp.data.remote.HelloWorldResponse

interface MyRepository {
    suspend fun doNetworkCall(): ResponseResult<HelloWorldResponse>
}