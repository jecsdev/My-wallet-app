package com.jecsdev.auth.di

import com.jecsdev.auth.domain.repository.AuthRepository
import com.jecsdev.auth.domain.usecase.AuthUseCases
import com.jecsdev.auth.domain.usecase.GetGoogleSignIn
import com.jecsdev.auth.domain.usecase.GetGoogleSignOut
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthUseCasesModule {
    @Provides
    @Singleton
    fun provideAuthUseCases(authRepository: AuthRepository): AuthUseCases {
        return AuthUseCases(
            getGoogleSignIn = GetGoogleSignIn(authRepository),
            getGoogleSignOut = GetGoogleSignOut(authRepository)
        )
    }
}