package com.pl.craftbidapp.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.pl.craftbidapp.ui.offerList.OfferListElement
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
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
    val tags: List<Tag>,
    val advertiserId: Long,
    val winnerId: Long,
    val avgBid: Double

)
fun OfferResponse.toOfferListElement() : OfferListElement {
    if (photos.isEmpty()) {
        return OfferListElement(
            id = id,
            title = title,
            avgBid = avgBid,
            bids = bids.size,
            photo = ""
        )
    }
    return OfferListElement(
        id = id,
        title = title,
        avgBid = avgBid,
        bids = bids.size,
        photo = photos[0]
    )
}
fun getBitmapFromURL(src: String?) : Bitmap?  {
    return try {
        Log.e("src", src!!)
        val url = URL(src)
        val connection = url.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val input = connection.inputStream
        val myBitmap = BitmapFactory.decodeStream(input)
        Log.e("Bitmap", "returned")
        myBitmap
    } catch (e: IOException) {
        e.printStackTrace()
        Log.e("Exception", e.message!!)
        null
    }
}