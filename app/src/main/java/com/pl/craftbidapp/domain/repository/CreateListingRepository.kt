package com.pl.craftbidapp.domain.repository

import com.pl.craftbidapp.ui.createListing.CreateListingResponse
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.ui.createListing.CreateListingRequest

interface CreateListingRepository {
    suspend fun createListing(createListingRequest: CreateListingRequest): ResponseResult<CreateListingResponse>
}