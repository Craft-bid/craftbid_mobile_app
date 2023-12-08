package com.pl.craftbidapp.domain.repository

import com.pl.craftbidapp.ui.createListing.CreateListingResponse
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.ui.createListing.CreateListingRequest
import okhttp3.MultipartBody

interface CreateListingRepository {
    suspend fun createListing(createListingRequest: CreateListingRequest): ResponseResult<CreateListingResponse>
    suspend fun addPhotosToListing(listingId: Long, photos: List<MultipartBody.Part>): ResponseResult<CreateListingResponse>
}