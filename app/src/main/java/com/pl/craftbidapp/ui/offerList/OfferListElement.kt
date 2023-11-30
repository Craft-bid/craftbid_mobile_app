package com.pl.craftbidapp.ui.offerList

data class OfferListElement(
    val id: Long,
    val title: String,
    val avgBid: Double,
    val bids:Int,
    val photo: String,
)