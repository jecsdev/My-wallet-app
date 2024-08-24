package com.jecsdev.mywalletapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.jecsdev.mywalletapp.ui.state.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
}