package com.pl.craftbidapp.data.remote

import com.pl.craftbidapp.data.OfferResponse
import retrofit2.Response
import retrofit2.http.GET

interface OfferListApi {
    @GET("api/v1/public/listings/search")
    suspend fun getOfferList(): Response<List<OfferResponse>>
}