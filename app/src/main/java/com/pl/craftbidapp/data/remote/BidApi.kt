package com.pl.craftbidapp.data.remote

import com.pl.craftbidapp.data.Bid
import com.pl.craftbidapp.data.BidCreateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
interface BidApi {
    @POST("api/v1/private/bids")
    suspend fun createBid(@Body bidCreateRequest: BidCreateRequest): Response<Bid>
}