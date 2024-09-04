package com.jecsdev.mywalletapp.feature_authentication.di

import com.jecsdev.mywalletapp.feature_authentication.data.repository.AuthRepositoryImplementation
import com.jecsdev.mywalletapp.feature_authentication.domain.repository.AuthRepository
import com.jecsdev.mywalletapp.presentation.signin.GoogleAuthClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthRepositoryModule {
    @Provides
    @Singleton
    fun providesAuthRepositoryImplementation(googleAuthClient: GoogleAuthClient): AuthRepository {
        return AuthRepositoryImplementation(googleAuthClient)
    }
}