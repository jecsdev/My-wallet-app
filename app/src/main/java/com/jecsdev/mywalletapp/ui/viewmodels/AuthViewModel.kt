package com.jecsdev.mywalletapp.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jecsdev.mywalletapp.ui.state.SignInState
import com.jecsdev.mywalletapp.feature_authentication.data.model.UserData
import com.jecsdev.mywalletapp.feature_authentication.repository.AuthRepository
import com.jecsdev.mywalletapp.presentation.signin.SignInResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel(){
    private val _authState = MutableStateFlow<SignInResult?>(null)

    private val _state = MutableStateFlow(SignInState())
    val signInState = _state.asStateFlow()

    /**
     * Handles the result in Sign In
     * @param result this is the Sign In Result
     */
    private fun onSignInResult(result: SignInResult){
        _state.update {signInState->
            signInState.copy(
                isSuccessful = result.data != null,
                isError = result.errorMessage
            )
        }
    }

    fun resetState(){
        _state.update { SignInState() }
    }

    fun signIn(context: Context) {
       viewModelScope.launch {
           val result = authRepository.googleSignIn(context)
            _authState.value = result
           onSignInResult(result)
       }
    }

    fun signOut() {
        authRepository.googleSignOut()
        _authState.value = null
    }

    fun getSignedUser(): UserData? = authRepository.getSignedInUser()

}