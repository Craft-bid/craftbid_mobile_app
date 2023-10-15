package com.plcoding.daggerhiltcourse.domain.repository

import com.plcoding.daggerhiltcourse.data.ResponseResult
import com.plcoding.daggerhiltcourse.data.remote.HelloWorldResponse

interface MyRepository {
    suspend fun doNetworkCall(): ResponseResult<HelloWorldResponse>
}