package com.pl.craftbidapp.ui.createListing

import android.app.Application
import android.content.SharedPreferences
import android.text.Editable
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pl.craftbidapp.data.CRAFT_BID_JWT_TOKEN
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.domain.repository.CreateListingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.Base64
import javax.inject.Inject

@HiltViewModel
class CreateListingViewModel @Inject constructor(
    private val createListingRepository: CreateListingRepository,
    private val application: Application,
    private val sharedPreferences: SharedPreferences
): ViewModel() {
    private val _createListingResult = MutableLiveData<CreateListingResponse>()
    val createListingResult: LiveData<CreateListingResponse>
        get() = _createListingResult

    fun createListing(title: String, description: String) {
        viewModelScope.launch {
            try {
                val userId = sharedPreferences.getLong("user_id", 0)
                val result = createListingRepository.createListing(CreateListingRequest(title, description, userId, false))

                if (result is ResponseResult.Success) {
                    Toast.makeText(application, "Listing created successfully", Toast.LENGTH_SHORT).show()
                    _createListingResult.value = CreateListingResponse(data = "Listing created successfully")
                } else {
                    _createListingResult.value = CreateListingResponse(error = -1)
                }
            } catch (e: Exception) {
                _createListingResult.value = CreateListingResponse(error = -1)
            }
        }
    }

    fun addPhotoToListing() {
        TODO("Not yet implemented")
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is createListing Fragment"
    }
    val text: LiveData<String> = _text
}