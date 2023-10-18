package com.plcoding.craftbidapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.plcoding.craftbidapp.domain.repository.MyRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MyService: Service() {

    @Inject
    lateinit var repository: MyRepository

    override fun onCreate() {
        super.onCreate()
        println("Essa")
        essa()

    }

    fun essa() {
        GlobalScope.launch {
            val response = repository.doNetworkCall()
            println(response)
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}