package com.pl.craftbidapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.data.remote.HelloWorldResponse
import com.pl.craftbidapp.domain.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(): ViewModel() {
    @Inject
    lateinit var myApi: MyRepository

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }

    val text: LiveData<String> = _text

    private val _helloWorld = MutableLiveData<String>()
    val helloWorldMessage: LiveData<String> = _helloWorld

    fun fetchHelloWorld() {
        GlobalScope.launch {
            val result: ResponseResult<HelloWorldResponse> =
                myApi.helloWorld()
            if (result is ResponseResult.Success) {
                _helloWorld.postValue(result.data.message)
            }
        }
    }
}