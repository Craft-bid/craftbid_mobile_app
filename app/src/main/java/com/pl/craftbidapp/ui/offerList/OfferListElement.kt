package com.example.craftbidkotlin.ui.offerList

data class OfferListElement(
    val id: Long,
    val title: String,
    val avgBid: Double,
    val bids:Int,
    val photos: List<String>

)