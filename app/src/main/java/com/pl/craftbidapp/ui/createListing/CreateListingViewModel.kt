package com.pl.craftbidapp.ui.createListing

import android.app.Application
import android.content.SharedPreferences
import android.graphics.BitmapFactory
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
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File
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
                    val listingId = result.data.data?.let { JSONObject(it).getLong("id") }
                    sharedPreferences.edit().putLong("listing_id", listingId!!).apply()
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
        viewModelScope.launch {
            try {
                val listingId = sharedPreferences.getLong("listing_id", 0)

                // Read the saved image file from internal storage
                val imageFile = File(application.filesDir, "images/temp.jpg")

                val imageByteArray = imageFile.readBytes()
                val imagePart = imageByteArray.toMultipart("photo")
                val result = createListingRepository.addPhotosToListing(listingId, listOf(imagePart))


                if (result is ResponseResult.Success) {
                    Toast.makeText(application, "Photo added successfully", Toast.LENGTH_SHORT).show()
                    _createListingResult.value = CreateListingResponse(data = "Photo added successfully")
                } else {
                    _createListingResult.value = CreateListingResponse(error = -1)
                }
            } catch (e: Exception) {
                _createListingResult.value = CreateListingResponse(error = -1)
            }
        }
    }

    fun ByteArray.toMultipart(partName: String): MultipartBody.Part {
        val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), this)
        return MultipartBody.Part.createFormData(partName, "temp.jpg", requestBody)
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is createListing Fragment"
    }
    val text: LiveData<String> = _text
}