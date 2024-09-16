package com.jecsdev.mywalletapp.ui.composables.bottomnavigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavController
import com.jecsdev.mywalletapp.presentation.navigation.BottomNavigationBarDestination

@Composable
fun BottomNavigationBar(navController: NavController?) {
    val items = listOf(
        BottomNavigationBarDestination.Home("Home", "home", Icons.Default.Home),
        BottomNavigationBarDestination.Expenses("Expenses", "expenses", Icons.Default.MoneyOff),
        BottomNavigationBarDestination.Income("Income", "income", Icons.Default.AttachMoney),
        BottomNavigationBarDestination.Budget("Budget", "budget", Icons.Default.PieChart),
        BottomNavigationBarDestination.Reports("Reports", "reports", Icons.Default.BarChart)
    )
    NavigationBar (contentColor = MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.background) {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.name) },
                label = { Text(item.name) },
                selected = navController?.currentDestination?.route == item.route,
                onClick = {
                    navController?.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = null)
}