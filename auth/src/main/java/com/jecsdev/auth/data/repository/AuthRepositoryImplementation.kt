package com.jecsdev.auth.data.repository

import android.content.Context
import com.jecsdev.auth.data.model.UserData
import com.jecsdev.auth.domain.repository.AuthRepository
import com.jecsdev.auth.data.service.GoogleAuthClient
import com.jecsdev.auth.domain.entities.SignInResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImplementation @Inject constructor(private val googleAuthClient: GoogleAuthClient) :
    AuthRepository {
    private var currentUserData: UserData? = null
    override suspend fun googleSignIn(context: Context): SignInResult {
        return withContext(Dispatchers.IO) {
            try {
                googleAuthClient.signIn(context)

                val signInResult = googleAuthClient.getUserSigned()
                if (signInResult.data != null) {
                    currentUserData = signInResult.data
                }

                signInResult
            } catch (e: Exception) {
                SignInResult(data = null, errorMessage = e.message)
            }
        }
    }

    override fun googleSignOut() {
        googleAuthClient.signOut()
        currentUserData = null
    }

    override fun getSignedInUser(): UserData? {
        return currentUserData ?: googleAuthClient.getSignedUser().also {data ->
            currentUserData = data
        }
    }

    override fun getUserId(): String? {
        return currentUserData?.userId
    }

}