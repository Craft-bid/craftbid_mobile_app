package com.pl.craftbidapp.data.remote

import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.ui.createListing.CreateListingRequest
import com.pl.craftbidapp.ui.createListing.CreateListingResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CreateListingApi {
    @POST("/api/v1/private/listings")
    suspend fun createListing(@Body createListingRequest: CreateListingRequest): Response<CreateListingResponse>

    @Multipart
    @POST("/api/v1/private/{listingId}/photos")
    suspend fun addPhotosToListing(
        @retrofit2.http.Path("listingId") listingId: Long,
        @Part photos: List<MultipartBody.Part>
    ): Response<CreateListingResponse>
}