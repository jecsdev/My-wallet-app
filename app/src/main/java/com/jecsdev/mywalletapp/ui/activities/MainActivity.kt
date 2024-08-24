package com.jecsdev.mywalletapp.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.rememberNavController
import com.jecsdev.mywalletapp.ui.viewmodels.AuthViewModel
import com.jecsdev.mywalletapp.R
import com.jecsdev.mywalletapp.presentation.navigation.NavGraph
import com.jecsdev.mywalletapp.ui.theme.MyWalletAppTheme
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
                    color = colorResource(R.color.phantom_gray_color)
                ) {
                    val navController = rememberNavController()
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

