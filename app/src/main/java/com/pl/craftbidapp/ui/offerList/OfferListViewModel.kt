package com.pl.craftbidapp.ui.offerList


import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pl.craftbidapp.data.OfferResponse
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.data.toOfferListElement
import com.pl.craftbidapp.domain.repository.OfferListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OfferListViewModel @Inject constructor(
    private val offerListRepository: OfferListRepository,
    private val application: Application,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _offerListElementList = MutableLiveData<List<OfferListElement>>()
    val offerListElementList: LiveData<List<OfferListElement>> get() = _offerListElementList

    init {
        viewModelScope.launch {
            val offerListResponse = offerListRepository.getOfferList(null)
            val offerListElementList = mutableListOf<OfferListElement>()
            if (offerListResponse is ResponseResult.Success) {
                for (offer: OfferResponse in offerListResponse.data) {
                    offerListElementList.add(offer.toOfferListElement())
                }
            }
            _offerListElementList.value = offerListElementList
        }

    }
}