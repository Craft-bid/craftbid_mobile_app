package com.pl.craftbidapp.ui.offerDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pl.craftbidapp.data.Bid
import com.pl.craftbidapp.data.BidCreateRequest
import com.pl.craftbidapp.data.OfferResponse
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.domain.repository.BidRepository
import com.pl.craftbidapp.domain.repository.OfferListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BidViewModel
@Inject constructor() : ViewModel() {
    @Inject
    lateinit var bidRepository: BidRepository

    val bidPrice = MutableLiveData<String>()
    val bidDescription = MutableLiveData<String>()
    val bidDaysToDeliver = MutableLiveData<String>()
    val listingId = MutableLiveData<Long>()

    private val _OfferDetailsData = MutableLiveData<Bid>()
    val OfferDetailsData: LiveData<Bid> = _OfferDetailsData

    fun submitBid() {
        val price = bidPrice.value?.toLongOrNull() ?: return
        val description = bidDescription.value ?: return
        val daysToDeliver = bidDaysToDeliver.value?.toLongOrNull() ?: return
        val listingId = listingId.value ?: return

        val bidRequest = BidCreateRequest(listingId, price, description, daysToDeliver)
        GlobalScope.launch {
            val result = bidRepository.createBid(bidRequest)
            if (result is ResponseResult.Success) {
                _OfferDetailsData.postValue(result.data)
                bidPrice.postValue("")
                bidDescription.postValue("")
                bidPrice.postValue("")
            }
        }
    }
}