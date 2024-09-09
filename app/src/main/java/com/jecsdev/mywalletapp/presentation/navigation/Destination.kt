package com.jecsdev.mywalletapp.presentation.navigation

import kotlinx.serialization.Serializable

/**
 * Class that handles the destinations from whole app.
 */
@Serializable
sealed class Destination() {
   @Serializable
   data object LogIn: Destination()
   @Serializable
   data object Home: Destination()
}