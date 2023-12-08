package com.pl.craftbidapp.data.repository

import android.app.Application
import android.security.keystore.UserNotAuthenticatedException
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.data.remote.CreateListingApi
import com.pl.craftbidapp.domain.repository.CreateListingRepository
import com.pl.craftbidapp.ui.createListing.CreateListingRequest
import com.pl.craftbidapp.ui.createListing.CreateListingResponse
import okhttp3.MultipartBody
import java.io.IOException
import javax.inject.Inject

class CreateListingRepositoryImpl @Inject constructor(
    private val api: CreateListingApi,
): CreateListingRepository {
    override suspend fun createListing(createListingRequest: CreateListingRequest): ResponseResult<CreateListingResponse> {
                return try {
                    val response = api.createListing(createListingRequest)
                    if (response.isSuccessful && response.body() != null) {
                        System.out.println("success")
                        ResponseResult.Success(response.body()!!)
                    } else {
                        System.out.println("failed")
                        ResponseResult.Error(UserNotAuthenticatedException())
                    }
                } catch (e: Throwable) {
                    System.out.println("error logging in")
                    e.printStackTrace()
                    ResponseResult.Error(IOException("Error logging in", e))
                }
    }

    override suspend fun addPhotosToListing(listingId: Long, photos: List<MultipartBody.Part>): ResponseResult<CreateListingResponse> {
        return try {
            val response = api.addPhotosToListing(listingId, photos)
            if (response.isSuccessful && response.body() != null) {
                System.out.println("success")
                ResponseResult.Success(response.body()!!)
            } else {
                System.out.println("failed")
                ResponseResult.Error(UserNotAuthenticatedException())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseResult.Error(e)
        }
    }

}
