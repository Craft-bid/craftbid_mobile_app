package com.pl.craftbidapp.data

import com.example.craftbidkotlin.ui.offerList.OfferListElement
import java.util.Date

class OfferResponse(
    val id: Long,
    val title: String,
    val ended: Boolean,
    val expirationDate: Date,
    val creationDate: Date,
    val description: String,
    val photos: List<String>,
    val bids: List<Bid>,
    val tags: List<Tag>, // Assuming Tag is a data class or type you've defined
    val advertiserId: Long,
    val winnerId: Long,
    val avgBid: Double

)
fun OfferResponse.toOfferListElement() = OfferListElement(
    id = id,
    title = title,
    avgBid = avgBid,
    bids = bids.size,
    photos = photos
)