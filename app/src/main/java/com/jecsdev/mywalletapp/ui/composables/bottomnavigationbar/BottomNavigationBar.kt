package com.jecsdev.mywalletapp.ui.composables.bottomnavigationbar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jecsdev.mywalletapp.presentation.navigation.BottomNavigationBarDestination

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavigationBarDestination.Home,
        BottomNavigationBarDestination.Expenses,
        BottomNavigationBarDestination.Income,
        BottomNavigationBarDestination.Budget,
        BottomNavigationBarDestination.Reports
    )
    NavigationBar (contentColor = MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.background) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { item ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == item.route::class.qualifiedName } == true
            NavigationBarItem(
                icon = {  Icon(
                    imageVector = ImageVector.vectorResource(item.icon),
                    contentDescription = item.name
                ) },
                label = { Text(item.name) },
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
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
    BottomNavigationBar(navController = rememberNavController())
}