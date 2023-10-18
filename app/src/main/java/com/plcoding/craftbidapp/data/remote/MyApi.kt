package com.plcoding.craftbidapp.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface MyApi {
    @GET("/api/v1/private/hello-world")
    suspend fun helloWorld(): Response<HelloWorldResponse>
}

data class HelloWorldResponse(val message: String)

