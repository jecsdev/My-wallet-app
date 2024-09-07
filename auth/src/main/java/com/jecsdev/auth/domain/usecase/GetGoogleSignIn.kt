package com.jecsdev.auth.domain.usecase

import android.content.Context
import com.jecsdev.auth.domain.entities.User

import com.jecsdev.auth.domain.repository.AuthRepository
import com.jecsdev.auth.utils.common.Result
import javax.inject.Inject

class GetGoogleSignIn @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(context: Context): Result<User> {
        val user = authRepository.googleSignIn(context).data?.toDomain()
        return if (user == null) {
            Result.Error(Exception("User not found or null"))
        } else {
            Result.Success(user)
        }
    }
}
