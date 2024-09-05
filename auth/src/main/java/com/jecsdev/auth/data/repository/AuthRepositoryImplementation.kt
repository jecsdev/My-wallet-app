package com.jecsdev.auth.data.repository

import android.content.Context
import com.jecsdev.auth.domain.repository.AuthRepository
import com.jecsdev.auth.data.service.GoogleAuthClient
import com.jecsdev.auth.domain.entities.SignInResult
import com.jecsdev.auth.domain.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImplementation @Inject constructor(private val googleAuthClient: GoogleAuthClient) :
    AuthRepository {
    private var currentUser: User? = null

    override suspend fun googleSignIn(context: Context): SignInResult {
        return withContext(Dispatchers.IO) {
            try {
                googleAuthClient.signIn(context)

                val signInResult = googleAuthClient.getUserSigned()
                if (signInResult.data != null) {
                    currentUser = signInResult.data.toDomain()
                }

                signInResult
            } catch (e: Exception) {
                SignInResult(data = null, errorMessage = e.message)
            }
        }
    }

    override fun googleSignOut() {
        googleAuthClient.signOut()
        currentUser = null
    }

    override fun getSignedInUser(): User? {
        return currentUser
    }

    override fun getUserId(): String? {
        return currentUser?.userId
    }

}