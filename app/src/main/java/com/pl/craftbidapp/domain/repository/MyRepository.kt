package com.pl.craftbidapp.domain.repository

import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.data.remote.HelloWorldResponse

interface MyRepository {
    suspend fun doNetworkCall(): ResponseResult<HelloWorldResponse>
}