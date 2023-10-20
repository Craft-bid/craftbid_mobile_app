package com.pl.craftbidapp.data.remote

import com.pl.craftbidapp.data.OfferResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface OfferListApi {
    @GET("/api/v1/public/listings/search")
    fun getOfferList(): Response<List<OfferResponse>>
}