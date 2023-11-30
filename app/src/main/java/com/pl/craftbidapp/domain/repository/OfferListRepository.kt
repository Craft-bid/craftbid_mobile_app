package com.pl.craftbidapp.domain.repository

import com.pl.craftbidapp.data.FilterParams
import com.pl.craftbidapp.data.OfferResponse
import com.pl.craftbidapp.data.ResponseResult

interface OfferListRepository {
    suspend fun getOfferList(filter: FilterParams?): ResponseResult<List<OfferResponse>>
    suspend fun getUserOffers(email: String): ResponseResult<List<OfferResponse>>
}