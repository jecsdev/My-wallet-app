package com.jecsdev.auth.domain.entities

import com.jecsdev.auth.data.model.UserData

/**
 * This class the Sign In result when user authenticates
 */
data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)


