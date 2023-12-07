package com.pl.craftbidapp.data.repository

import android.app.Application
import android.security.keystore.UserNotAuthenticatedException
import com.pl.craftbidapp.data.FilterParams
import com.pl.craftbidapp.data.OfferResponse
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.data.remote.OfferListApi
import com.pl.craftbidapp.domain.repository.OfferListRepository
import java.io.IOException
import javax.inject.Inject

class OfferListRepositoryImpl @Inject constructor(
    private val api: OfferListApi,
    private val appContext: Application
): OfferListRepository {
    override suspend fun getOfferList(filter: FilterParams?): ResponseResult<List<OfferResponse>> {
        val queryParameters = mapOf(
            "title" to (filter?.title ?: ""),
            "advertiserName" to filter?.advertiserName,
            "winnerName" to filter?.winnerName,
            "tags" to filter?.tags?.joinToString(","),
            "minPrice" to filter?.minPrice,
            "maxPrice" to filter?.maxPrice,
            "pageNumber" to filter?.pageable?.pageNumber,
            "pageSize" to filter?.pageable?.pageSize
        ).filterValues { it != null }

        return try {
            //val response = api.getOfferList(queryParameters)
            val response = api.getOfferList(queryParameters)

            if (response.isSuccessful && response.body() != null) {
                ResponseResult.Success(response.body()!!)
            } else {
                ResponseResult.Error(UserNotAuthenticatedException())
            }
        } catch (e: Throwable) {
            ResponseResult.Error(IOException("Error getting offer list", e))
        }
    }

    override suspend fun getUserOffers(email:String):ResponseResult<List<OfferResponse>>{
        return try {
            val response = api.getUserOffers(email)

            if (response.isSuccessful && response.body() != null) {
                ResponseResult.Success(response.body()!!)
            } else {
                ResponseResult.Error(UserNotAuthenticatedException())
            }
        } catch (e: Throwable) {
            ResponseResult.Error(IOException("Error getting offer list", e))
        }
    }

    override suspend fun getOffer(id: Long): ResponseResult<OfferResponse> {
        return try {
            val response = api.getOffer(id)

            if (response.isSuccessful && response.body() != null) {
                ResponseResult.Success(response.body()!!)
            } else {
                ResponseResult.Error(UserNotAuthenticatedException())
            }
        } catch (e: Throwable) {
            ResponseResult.Error(IOException("Error getting offer", e))
        }
    }
}