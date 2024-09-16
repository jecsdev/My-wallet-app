package com.jecsdev.mywalletapp.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * This class handles the destinations from the bottom navigation bar.
 */
sealed class BottomNavigationBarDestination(
    open val name: String, open val route: String,
    open val icon: ImageVector
) {
    data class Home(
        override val name: String, override val route: String,
        override val icon: ImageVector
    ) : BottomNavigationBarDestination(name, route, icon)

    data class Expenses(
        override val name: String, override val route: String,
        override val icon: ImageVector
    ) : BottomNavigationBarDestination(name, route, icon)

    data class Income(
        override val name: String, override val route: String,
        override val icon: ImageVector
    ) : BottomNavigationBarDestination(name, route, icon)

    data class Budget(
        override val name: String, override val route: String,
        override val icon: ImageVector
    ) : BottomNavigationBarDestination(name, route, icon)

    data class Reports(
        override val name: String, override val route: String,
        override val icon: ImageVector
    ) : BottomNavigationBarDestination(name, route, icon)
}