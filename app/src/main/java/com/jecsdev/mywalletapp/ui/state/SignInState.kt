package com.jecsdev.mywalletapp.ui.state

import com.jecsdev.auth.domain.entities.User

/**
 * Manages the state from Sign In.
 * @param isSuccessful returns if was a successful transaction.
 * @param isLoading returns if the Sign in is loading.
 * @param isError returns if there is an error.
 */
data class SignInState(
    val isSuccessful: Boolean = false,
    val isUserLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
    val isError: String? = null,
    var user: User? = null
)
