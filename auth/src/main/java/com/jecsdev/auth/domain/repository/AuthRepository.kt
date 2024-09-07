package com.jecsdev.auth.domain.repository

import android.content.Context
import com.jecsdev.auth.data.model.UserData
import com.jecsdev.auth.domain.entities.SignInResult

/**
 * Authentication Repository interface.
 */
interface AuthRepository {
    suspend fun googleSignIn(context: Context): SignInResult
    fun googleSignOut()
    fun getSignedInUser(): UserData?
    fun getUserId(): String?
}