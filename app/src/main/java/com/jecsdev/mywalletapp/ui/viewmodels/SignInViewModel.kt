package com.jecsdev.mywalletapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.jecsdev.mywalletapp.ui.state.SignInState
import com.jecsdev.mywalletapp.presentation.signin.SignInResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * This class represents the Sign In ViewModel
 */
@HiltViewModel
class SignInViewModel @Inject constructor(): ViewModel(){

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    /**
     * Handles the result in Sign In
     * @param result this is the Sign In Result
     */
    fun onSignInResult(result: SignInResult){
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
}