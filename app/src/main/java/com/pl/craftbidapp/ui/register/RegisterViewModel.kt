package com.pl.craftbidapp.ui.register

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pl.craftbidapp.data.Bid
import com.pl.craftbidapp.data.LoggedInUser
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val application: Application
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Register to CraftBid"
    }
    val text: LiveData<String> = _text

    val _name = MutableLiveData<String>()
    val _email = MutableLiveData<String>()
    val _password = MutableLiveData<String>()


    private val _OfferDetailsData = MutableLiveData<LoggedInUser>()
    val OfferDetailsData: LiveData<LoggedInUser> = _OfferDetailsData

    fun submitBid() {
        val name = _name.value ?: return
        val email = _email.value ?: return
        val password = _password.value ?: return

        GlobalScope.launch {
            val result = authRepository.register(name, email, password)
            if (result is ResponseResult.Success) {
                _OfferDetailsData.postValue(result.data)
                _name.postValue("")
                _email.postValue("")
                _password.postValue("")
            }
        }
    }
}