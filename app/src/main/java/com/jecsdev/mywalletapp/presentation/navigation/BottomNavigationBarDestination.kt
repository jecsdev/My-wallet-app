package com.jecsdev.mywalletapp.presentation.navigation

import com.jecsdev.mywalletapp.R
import kotlinx.serialization.Serializable

/**
 * This class handles the destinations from the bottom navigation bar.
 */
@Serializable
sealed class BottomNavigationBarDestination<T>(
    open val name: String, open val icon: Int, open val route: T
) {
    @Serializable
    data object Home: BottomNavigationBarDestination<Destination.Home>(name = "Home", icon = R.drawable.home_filled, Destination.Home)
    @Serializable
    data object Expenses: BottomNavigationBarDestination<Destination.Expenses>(name = "Expenses", icon = R.drawable.expenses_filled, Destination.Expenses)
    @Serializable
    data object Income: BottomNavigationBarDestination<Destination.Income>(name = "Income", icon = R.drawable.income_filled, Destination.Income)
    @Serializable
    data object Budget: BottomNavigationBarDestination<Destination.Budget>(name = "Budget", icon = R.drawable.budget_filled, Destination.Budget)
    @Serializable
    data object Reports: BottomNavigationBarDestination<Destination.Reports>(name = "Reports", icon = R.drawable.pie_chart_filled, Destination.Reports)
}
