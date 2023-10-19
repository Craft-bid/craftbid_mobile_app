package com.pl.craftbidapp.ui.register

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pl.craftbidapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val application: Application
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is register Fragment"
    }
    val text: LiveData<String> = _text
}