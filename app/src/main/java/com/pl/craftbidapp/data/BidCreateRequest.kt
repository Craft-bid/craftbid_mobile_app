package com.pl.craftbidapp.data

data class BidCreateRequest(
    val listingId: Long,
    val price: Long,
    val description: String,
    val daysToDeliver: Long
)