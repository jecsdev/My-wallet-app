package com.jecsdev.mywalletapp.presentation.signin

import com.jecsdev.mywalletapp.feature_authentication.data.model.UserData

/**
 * This class the Sign In result when user authenticates
 */
data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)


