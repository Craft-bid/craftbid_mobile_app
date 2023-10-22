package com.pl.craftbidapp.ui.createListing

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CreateListingViewModel @Inject constructor(): ViewModel() {
    fun createListing(title: Editable, description: Editable) {
        if (title.toString().isEmpty() || description.toString().isEmpty()) {
            return
        }

    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is createListing Fragment"
    }
    val text: LiveData<String> = _text
}