package com.jecsdev.auth.domain.repository

import android.content.Context
import com.jecsdev.auth.domain.entities.SignInResult
import com.jecsdev.auth.domain.entities.User

/**
 * Authentication Repository interface.
 */
interface AuthRepository {
    suspend fun googleSignIn(context: Context): SignInResult
    fun googleSignOut()
    fun getSignedInUser(): User?
    fun getUserId(): String?
}