package com.pl.craftbidapp.data.repository

import android.app.Application
import android.security.keystore.UserNotAuthenticatedException
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.data.remote.CreateListingApi
import com.pl.craftbidapp.domain.repository.CreateListingRepository
import com.pl.craftbidapp.ui.createListing.CreateListingRequest
import com.pl.craftbidapp.ui.createListing.CreateListingResponse
import java.io.IOException
import javax.inject.Inject

class CreateListingRepositoryImpl @Inject constructor(
    private val api: CreateListingApi,
    private val appContext: Application
): CreateListingRepository {
    override suspend fun createListing(createListingRequest: CreateListingRequest): ResponseResult<CreateListingResponse> {
        return try {
                val response = api.createListing(createListingRequest)
                if (response.isSuccessful) {
                    ResponseResult.Success(response.body()!!)
                } else{
                    ResponseResult.Error(UserNotAuthenticatedException())
                }
        } catch (e: Throwable) {
            ResponseResult.Error(IOException("Error logging in", e))
        }
    }

}
