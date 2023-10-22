package com.pl.craftbidapp.data.repository

import android.app.Application
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.data.remote.CreateListingApi
import com.pl.craftbidapp.domain.repository.CreateListingRepository
import com.pl.craftbidapp.ui.createListing.CreateListingRequest
import com.pl.craftbidapp.ui.createListing.CreateListingResponse
import javax.inject.Inject

class CreateListingRepositoryImpl @Inject constructor(
    private val api: CreateListingApi,
    private val appContext: Application
): CreateListingRepository {
    override suspend fun createListing(createListingRequest: CreateListingRequest): ResponseResult<CreateListingResponse> {
        TODO("Not yet implemented")
    }

}
