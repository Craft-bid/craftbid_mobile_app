package com.pl.craftbidapp

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.pl.craftbidapp.data.CRAFT_BID_JWT_TOKEN
import com.pl.craftbidapp.data.LoggedInUser
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.domain.repository.AuthRepository
import com.pl.craftbidapp.domain.repository.MyRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    @Named("hello2")
    lateinit var hello: String

    @Inject
    lateinit var myRepository: MyRepository

    @Inject
    lateinit var authRepository: AuthRepository

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println(hello)
        GlobalScope.launch {
            val result: ResponseResult<LoggedInUser> =
                authRepository.login("elon@gmail.com", "elonNowak")
            if (result is ResponseResult.Success) {
                println(result.data)
                sharedPreferences.edit().putString(CRAFT_BID_JWT_TOKEN, result.data.token)
                    .apply()
            }

            val response = myRepository.doNetworkCall()
            println(response)
        }
    }
}