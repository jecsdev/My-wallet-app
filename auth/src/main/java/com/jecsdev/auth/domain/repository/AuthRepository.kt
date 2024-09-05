package com.jecsdev.auth.domain.repository

import android.content.Context
import com.jecsdev.auth.data.model.UserData
import com.jecsdev.auth.signin.SignInResult

/**
 * Authentication Repository interface.
 */
interface AuthRepository {
    suspend fun googleSignIn(context: Context): SignInResult
    fun googleSignOut()
    fun getSignedInUser(): UserData?
    fun getUserId(): String?
}