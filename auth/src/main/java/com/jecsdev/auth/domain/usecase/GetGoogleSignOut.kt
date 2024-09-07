package com.jecsdev.auth.domain.usecase

import com.jecsdev.auth.domain.repository.AuthRepository
import javax.inject.Inject

class GetGoogleSignOut @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke() {
        authRepository.googleSignOut()
    }
}