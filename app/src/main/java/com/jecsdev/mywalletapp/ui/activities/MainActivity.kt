package com.jecsdev.mywalletapp.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jecsdev.mywalletapp.ui.viewmodels.AuthViewModel
import com.jecsdev.mywalletapp.presentation.navigation.NavGraph
import com.jecsdev.mywalletapp.ui.composables.bottomnavigationbar.BottomNavigationBar
import com.jecsdev.mywalletapp.ui.theme.MyWalletAppTheme
import com.jecsdev.mywalletapp.ui.theme.lightLavender
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyWalletAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = lightLavender
                ) {
                    val systemUiController = rememberSystemUiController()
                    val navController = rememberNavController()
                    systemUiController.setSystemBarsColor(lightLavender)
                    Scaffold(
                        bottomBar = {
                            val userState by authViewModel.state.collectAsStateWithLifecycle()
                            if (userState.isUserLoggedIn) {
                                BottomNavigationBar(navController = navController)
                            }
                        },
                    ) { paddingValues ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                                .padding(top = paddingValues.calculateTopPadding()),
                            contentAlignment = Alignment.Center
                        ) {
                            NavGraph(
                                context = this@MainActivity,
                                navController = navController,
                                authViewModel = authViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}

