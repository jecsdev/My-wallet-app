package com.jecsdev.auth.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.jecsdev.auth.signin.GoogleAuthClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Handles the dependency injection for authentication.
 */
@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesGoogleAuthClient(@ApplicationContext context: Context): GoogleAuthClient {
        return GoogleAuthClient(context)
    }

}