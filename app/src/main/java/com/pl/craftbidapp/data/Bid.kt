package com.pl.craftbidapp.data

import java.util.Date

class Bid(
    val id: Long,
    val price: Double,
    val description: String,
    val creationDate: Date,
    val daysToDeliver: Int,
    val bidderName: String,
    val bidderSurname: String,
    val bidderCountry: String,
    val bidderCity: String,
    val bidderRating: Double,
    val photoURL: String,
    val bidderId: Long
)