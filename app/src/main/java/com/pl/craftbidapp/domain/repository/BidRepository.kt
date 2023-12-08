package com.pl.craftbidapp.domain.repository

import com.pl.craftbidapp.data.Bid
import com.pl.craftbidapp.data.BidCreateRequest
import com.pl.craftbidapp.data.FilterParams
import com.pl.craftbidapp.data.OfferResponse
import com.pl.craftbidapp.data.ResponseResult

interface BidRepository {
    suspend fun createBid(bidCreateRequest: BidCreateRequest): ResponseResult<Bid>
}