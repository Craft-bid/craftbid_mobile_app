package com.pl.craftbidapp.data.remote

import com.pl.craftbidapp.ui.createListing.CreateListingResponse
import retrofit2.Response
import retrofit2.http.POST

interface CreateListingApi {
    @POST("/api/v1/private/listings")
    fun createListing(): Response<CreateListingResponse>
}