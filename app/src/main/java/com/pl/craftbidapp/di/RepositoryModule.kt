package com.pl.craftbidapp.di

import com.pl.craftbidapp.data.repository.AuthRepositoryImpl
import com.pl.craftbidapp.data.repository.MyRepositoryImpl
import com.pl.craftbidapp.data.repository.OfferListRepositoryImpl
import com.pl.craftbidapp.domain.repository.AuthRepository
import com.pl.craftbidapp.domain.repository.MyRepository
import com.pl.craftbidapp.domain.repository.OfferListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(
        myRepositoryImpl: MyRepositoryImpl
    ): MyRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindOfferListRepository(
        offerListRepositoryImpl: OfferListRepositoryImpl
    ): OfferListRepository
}