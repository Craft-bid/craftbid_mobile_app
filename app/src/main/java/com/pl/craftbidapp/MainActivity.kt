package com.pl.craftbidapp

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pl.craftbidapp.data.CRAFT_BID_JWT_TOKEN
import com.pl.craftbidapp.data.LoggedInUser
import com.pl.craftbidapp.data.ResponseResult
import com.pl.craftbidapp.domain.repository.AuthRepository
import com.pl.craftbidapp.domain.repository.MyRepository
import com.pl.craftbidapp.ui.login.LoginFragment
import com.pl.craftbidapp.ui.register.RegisterFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    @Named("hello2")
    lateinit var hello: String

    @Inject
    lateinit var myRepository: MyRepository

    @Inject
    lateinit var authRepository: AuthRepository

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if (savedInstanceState == null) {
            currentFragment = LoginFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, currentFragment)
                .commit()
        }

        findViewById<Button>(R.id.buttonSwitch).setOnClickListener {
            switchFragment()
        }
    }

    private fun switchFragment() {
        currentFragment = if (currentFragment is LoginFragment) RegisterFragment() else LoginFragment()

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .replace(R.id.fragmentContainer, currentFragment)
            .commit()
    }
}