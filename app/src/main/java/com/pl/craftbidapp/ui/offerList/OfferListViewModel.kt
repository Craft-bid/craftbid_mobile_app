package com.example.craftbidkotlin.ui.offerList


import android.app.Application
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pl.craftbidapp.data.OfferResponse
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.data.toOfferListElement
import com.pl.craftbidapp.domain.repository.AuthRepository
import com.pl.craftbidapp.domain.repository.OfferListRepository
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

class OfferListViewModel @Inject constructor(
    private val offerListRepository: OfferListRepository,
    private val application: Application,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    // Define LiveData to hold the list of offers
    private val _offerListElementList = MutableLiveData<List<OfferListElement>>()
    val offerListElementList: LiveData<List<OfferListElement>> get() = _offerListElementList
    private val executor: Executor =
        Executors.newSingleThreadExecutor() // change according to your requirements

    private val handler = Handler(Looper.getMainLooper())
    fun runInBackground(runnable: Runnable?) {
        executor.execute(runnable)
    }
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
//            handler.post(Runnable {
//                _offerListElementList.value = offerListElementList
//            })
        }

    }



    // You can add functions to update the offer list if needed
}