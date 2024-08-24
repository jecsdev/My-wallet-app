package com.jecsdev.mywalletapp.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jecsdev.mywalletapp.ui.viewmodels.AuthViewModel
import com.jecsdev.mywalletapp.ui.screens.home.HomeScreen
import com.jecsdev.mywalletapp.ui.screens.login.LogInScreen
import kotlinx.coroutines.launch

@Composable
fun NavGraph(
    context: Context,
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val lifeCycleOwner = LocalLifecycleOwner.current
    NavHost(navController = navController, startDestination = Destination.LogIn.route) {
        composable(Destination.LogIn.route) {
            val state by authViewModel.signInState.collectAsStateWithLifecycle()

            //Sign in user if there a signed user automatically.
            LaunchedEffect(key1 = Unit) {
                if (authViewModel.getSignedUser() != null) {
                    navController.navigate(Destination.Home.route)
                } else if (state.isSuccessful) {
                    navController.navigate(Destination.Home.route)
                    authViewModel.resetState()
                }
            }

            LogInScreen(state = state, onSignInClick = {
                lifeCycleOwner.lifecycleScope.launch {
                    authViewModel.signIn(context)
                }
            })
        }
        composable(Destination.Home.route) {
            HomeScreen(
                userData = authViewModel.getSignedUser(),
                onSignOut = {
                    lifeCycleOwner.lifecycleScope.launch {
                        authViewModel.signOut()
                        navController.popBackStack()
                    }
                },
                navController = navController
            )
        }
    }
}