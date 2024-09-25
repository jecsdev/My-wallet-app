package com.jecsdev.mywalletapp.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jecsdev.mywalletapp.ui.state.SignInState
import com.jecsdev.auth.domain.entities.User
import com.jecsdev.auth.domain.usecase.GetGoogleSignIn
import com.jecsdev.auth.domain.usecase.GetGoogleSignOut
import com.jecsdev.auth.utils.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getGoogleSignIn: GetGoogleSignIn,
    private val getGoogleSignOut: GetGoogleSignOut
) : ViewModel() {
    private val _authState = MutableStateFlow(SignInState())

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state.asStateFlow()

    /**
     * Handles the result in Sign In
     * @param result this is the Sign In Result
     */
    private fun onSignInResult(result: Result<User>) {
        _state.update { signInState ->
            signInState.copy(
                isSuccessful = result is Result.Success,
                isUserLoggedIn = result is Result.Success,
                isError = if (result is Result.Error) result.exception.message else null
            )
        }
        _isUserLoggedIn.value = result is Result.Success
    }

    private fun resetState() {
        _authState.value = SignInState()
        _isUserLoggedIn.value = false
        _state.update { SignInState() }
    }

    fun signIn(context: Context) {
        viewModelScope.launch {
            val result = getGoogleSignIn(context)
            _authState.value = if (result is Result.Success) {
                SignInState(
                    isSuccessful = true,
                    isUserLoggedIn = true,
                    isLoading = false,
                    isError = null,
                    user = result.data
                )
            } else {
                SignInState(isError = (result as Result.Error).exception.message)
            }
            onSignInResult(result)
        }
    }

    fun signOut() {
        getGoogleSignOut()
        resetState()
    }

    fun getSignedUser(): User? {
        return _authState.value.user
    }

}