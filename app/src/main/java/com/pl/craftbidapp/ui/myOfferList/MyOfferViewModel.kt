package com.pl.craftbidapp.ui.offerList


import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pl.craftbidapp.data.CRAFT_BID_JWT_TOKEN
import com.pl.craftbidapp.data.OfferResponse
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.data.toOfferListElement
import com.pl.craftbidapp.domain.repository.OfferListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.Base64
import javax.inject.Inject


@HiltViewModel
class MyOfferViewModel @Inject constructor(
    private val offerListRepository: OfferListRepository,
    private val application: Application,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _favListElementList = MutableLiveData<List<OfferListElement>>()
    val favListElementList: LiveData<List<OfferListElement>> get() = _favListElementList

    init {
        val token = sharedPreferences.getString(CRAFT_BID_JWT_TOKEN, null)
        val decoded = decodeToken(token!!)
        val jsonObj = JSONObject(decoded)
        val email = jsonObj.getString("sub")
        viewModelScope.launch {
            val offerListResponse = offerListRepository.getUserOffers(email)
            val offerListElementList = mutableListOf<OfferListElement>()
            if (offerListResponse is ResponseResult.Success) {
                for (offer: OfferResponse in offerListResponse.data) {
                    offerListElementList.add(offer.toOfferListElement())
                }
            }
            _favListElementList.value = offerListElementList
        }

    }
    private fun decodeToken(jwt: String): String {
        val parts = jwt.split(".")
        return try {
            val charset = charset("UTF-8")
            val header = String(Base64.getUrlDecoder().decode(parts[0].toByteArray(charset)), charset)
            val payload = String(Base64.getUrlDecoder().decode(parts[1].toByteArray(charset)), charset)
            "$header"
            "$payload"
        } catch (e: Exception) {
            "Error parsing JWT: $e"
        }
    }
}