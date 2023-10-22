package com.pl.craftbidapp.data.remote

import com.pl.craftbidapp.data.OfferResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface OfferListApi {
    @GET("api/v1/public/listings/search")
    @JvmSuppressWildcards
    suspend fun getOfferList(@QueryMap params: Map<String, Any?>): Response<List<OfferResponse>>
}