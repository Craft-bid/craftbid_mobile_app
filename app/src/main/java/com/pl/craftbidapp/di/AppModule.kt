package com.pl.craftbidapp.di

import android.content.Context
import android.content.SharedPreferences
import com.pl.craftbidapp.data.CRAFT_BID_BASE_URL
import com.pl.craftbidapp.data.AuthInterceptor
import com.pl.craftbidapp.data.remote.AuthApi
import com.pl.craftbidapp.data.remote.BidApi
import com.pl.craftbidapp.data.remote.MyApi
import com.pl.craftbidapp.data.remote.OfferListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyApi(authInterceptor: AuthInterceptor): MyApi {
        return Retrofit.Builder()
            .baseUrl(CRAFT_BID_BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(authInterceptor)
                    .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBidApi(authInterceptor: AuthInterceptor): BidApi {
        return Retrofit.Builder()
            .baseUrl(CRAFT_BID_BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(authInterceptor)
                    .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BidApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl(CRAFT_BID_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOfferListApi(authInterceptor: AuthInterceptor): OfferListApi {
        return Retrofit.Builder()
            .baseUrl(CRAFT_BID_BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(authInterceptor)
                    .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OfferListApi::class.java)
    }

    @Singleton
    @Provides
    fun provideServiceInterceptor(sharedPreferences: SharedPreferences): AuthInterceptor =
        AuthInterceptor(sharedPreferences)

    @Singleton
    @Provides
    fun sharedPref(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("JWT_AUTH_TOKEN", Context.MODE_PRIVATE)


    @Provides
    @Singleton
    @Named("hello1")
    fun provideString1() = "Hello 1"

    @Provides
    @Singleton
    @Named("hello2")
    fun provideString2() = "Hello 2"
}