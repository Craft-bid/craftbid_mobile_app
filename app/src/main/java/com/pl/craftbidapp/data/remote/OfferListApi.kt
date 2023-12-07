package com.pl.craftbidapp.data.remote

import com.pl.craftbidapp.data.OfferResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface OfferListApi {
    @GET("api/v1/public/listings/search")
    @JvmSuppressWildcards
    suspend fun getOfferList(@QueryMap params: Map<String, Any?>): Response<List<OfferResponse>>

    @GET("api/v1/public/users/offers/{email}")
    suspend fun getUserOffers(@Path("email") email: String): Response<List<OfferResponse>>

    @GET("api/v1/public/listings/{id}")
    suspend fun getOffer(@Path("id") id: Long): Response<OfferResponse>
}