package com.pl.craftbidapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.pl.craftbidapp.domain.repository.MyRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

//@AndroidEntryPoint
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
            val response = repository.helloWorld()
            println(response)
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}