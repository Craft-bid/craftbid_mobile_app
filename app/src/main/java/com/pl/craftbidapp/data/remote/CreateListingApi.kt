package com.pl.craftbidapp.data.remote

import com.pl.craftbidapp.ui.createListing.CreateListingRequest
import com.pl.craftbidapp.ui.createListing.CreateListingResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface CreateListingApi {
    @POST("/api/v1/private/listings")
    fun createListing(@QueryMap params: Map<String, Any?>): Response<CreateListingResponse>
}