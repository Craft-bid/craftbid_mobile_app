package com.pl.craftbidapp

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pl.craftbidapp.domain.repository.AuthRepository
import com.pl.craftbidapp.domain.repository.MyRepository
import com.pl.craftbidapp.ui.login.LoginFragment
import com.pl.craftbidapp.ui.register.RegisterFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), OnSwitchListener {
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

    }

    override fun onSwitch() {
        switchFragment()
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