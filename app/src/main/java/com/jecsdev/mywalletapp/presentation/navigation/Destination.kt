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
   @Serializable
   data object Expenses: Destination()
   @Serializable
   data object Income: Destination()
   @Serializable
   data object Budget: Destination()
   @Serializable
   data object Reports: Destination()
}