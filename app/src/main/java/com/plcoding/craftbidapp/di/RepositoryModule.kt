package com.plcoding.craftbidapp.di

import com.plcoding.craftbidapp.data.repository.AuthRepositoryImpl
import com.plcoding.craftbidapp.data.repository.MyRepositoryImpl
import com.plcoding.craftbidapp.domain.repository.AuthRepository
import com.plcoding.craftbidapp.domain.repository.MyRepository
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
}