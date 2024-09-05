package com.jecsdev.auth.di

import com.jecsdev.auth.data.repository.AuthRepositoryImplementation
import com.jecsdev.auth.domain.repository.AuthRepository
import com.jecsdev.auth.signin.GoogleAuthClient
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