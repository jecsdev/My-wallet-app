package com.jecsdev.mywalletapp.presentation.navigation

import android.content.Context
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jecsdev.mywalletapp.ui.screens.budget.BudgetScreen
import com.jecsdev.mywalletapp.ui.screens.expenses.ExpensesScreen
import com.jecsdev.mywalletapp.ui.viewmodels.AuthViewModel
import com.jecsdev.mywalletapp.ui.screens.home.HomeScreen
import com.jecsdev.mywalletapp.ui.screens.income.IncomeScreen
import com.jecsdev.mywalletapp.ui.screens.login.LogInScreen
import com.jecsdev.mywalletapp.ui.screens.reports.ReportsScreen
import kotlinx.coroutines.launch

@Composable
fun NavGraph(
    context: Context,
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val lifeCycleOwner = LocalLifecycleOwner.current
    val state by authViewModel.state.collectAsStateWithLifecycle()
    NavHost(navController = navController, startDestination = Destination.LogIn) {
        composable<Destination.LogIn>(
            exitTransition = { slideOutToLeft(this) }
        ) {
            //Sign in user if there a signed user automatically.
            LaunchedEffect(key1 = Unit) {
                if (authViewModel.getSignedUser() != null) {
                    navController.navigate(Destination.Home)
                }
            }

            LaunchedEffect(key1 = state.isSuccessful) {
                if (state.isSuccessful) {
                    navController.navigate(Destination.Home)
                }
            }

            LogInScreen(state = state, onSignInClick = {
                lifeCycleOwner.lifecycleScope.launch {
                    authViewModel.signIn(context = context)
                }
            })
        }
        composable<Destination.Home>(
            enterTransition = { slideInToLeft(this) },
            exitTransition = { slideOutToRight(this) }
        ) {
            HomeScreen(
                userData = authViewModel.getSignedUser(),
                onSignOut = {
                    lifeCycleOwner.lifecycleScope.launch {
                        authViewModel.signOut()
                        navController.navigateUp()
                    }
                },
                navController = navController
            )
        }
        composable<Destination.Expenses>(
            enterTransition = { slideInToLeft(this) }) {
            ExpensesScreen()
        }
        composable<Destination.Income>(
            enterTransition = { slideInToLeft(this) }) {
            IncomeScreen()
        }
        composable<Destination.Budget>(
            enterTransition = { slideInToLeft(this) }) {
            BudgetScreen()
        }
        composable<Destination.Reports>(
            enterTransition = { slideInToLeft(this) }) {
            ReportsScreen()
        }
    }
}

fun slideInToLeft(scope: AnimatedContentTransitionScope<NavBackStackEntry>): EnterTransition {
    return scope.slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(300)
    )
}

fun slideInToRight(scope: AnimatedContentTransitionScope<NavBackStackEntry>): EnterTransition {
    return scope.slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(300)
    )
}

fun slideOutToLeft(scope: AnimatedContentTransitionScope<NavBackStackEntry>): ExitTransition {
    return scope.slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(300)
    )
}

fun slideOutToRight(scope: AnimatedContentTransitionScope<NavBackStackEntry>): ExitTransition {
    return scope.slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(300)
    )
}