package com.pl.craftbidapp.ui.offerDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pl.craftbidapp.data.OfferResponse
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.domain.repository.OfferListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfferDetailsViewModel
@Inject constructor() : ViewModel() {
    @Inject
    lateinit var offerListRepository: OfferListRepository

    private val _text = MutableLiveData<String>().apply {
        value = "This is offerdetails Fragment"
    }

    val text: LiveData<String> = _text

    private val _OfferDetailsData = MutableLiveData<OfferResponse>()
    val OfferDetailsData: LiveData<OfferResponse> = _OfferDetailsData

    fun fetchOfferDetails(offerId: Long) {
        GlobalScope.launch {
            val result: ResponseResult<OfferResponse> =
                offerListRepository.getOffer(offerId)
            if (result is ResponseResult.Success) {
                _OfferDetailsData.postValue(result.data)
            }
        }
    }
}