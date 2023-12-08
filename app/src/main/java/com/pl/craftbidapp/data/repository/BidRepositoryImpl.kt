package com.pl.craftbidapp.data.repository

import android.app.Application
import android.security.keystore.UserNotAuthenticatedException
import com.pl.craftbidapp.data.Bid
import com.pl.craftbidapp.data.BidCreateRequest
import com.pl.craftbidapp.data.FilterParams
import com.pl.craftbidapp.data.OfferResponse
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.data.remote.BidApi
import com.pl.craftbidapp.data.remote.OfferListApi
import com.pl.craftbidapp.domain.repository.BidRepository
import com.pl.craftbidapp.domain.repository.OfferListRepository
import java.io.IOException
import javax.inject.Inject

class BidRepositoryImpl @Inject constructor(
    private val api: BidApi
): BidRepository {
    override suspend fun createBid(bidCreateRequest: BidCreateRequest): ResponseResult<Bid> {
        return try {
            val response = api.createBid(bidCreateRequest)

            if (response.isSuccessful && response.body() != null) {
                ResponseResult.Success(response.body()!!)
            } else {
                ResponseResult.Error(UserNotAuthenticatedException())
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            ResponseResult.Error(IOException("Error creating bid", e))
        }
    }
}