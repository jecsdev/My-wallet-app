package com.jecsdev.mywalletapp.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jecsdev.mywalletapp.ui.state.SignInState
import com.jecsdev.mywalletapp.R
import com.jecsdev.mywalletapp.presentation.uihelpers.InputType
import com.jecsdev.mywalletapp.ui.composables.textfield.SimpleTextField
import com.jecsdev.mywalletapp.ui.theme.darkSteelBlue
import com.jecsdev.mywalletapp.ui.theme.googleButtonColor

/**
 * Composable that shows Sign In Screen
 * @param state Sign in state
 * @param onSignInClick capture sign in click
 */
@Composable
fun LogInScreen(
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(key1 = state.isError) {
        state.isError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            Image(
                painterResource(
                    id = R.drawable.my_wallet_app_logo
                ),
                contentDescription = null,
                modifier = Modifier
                    .height(240.dp)
                    .width(360.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            SimpleTextField(
                textTyped = email,
                onValueChange = {
                    email = it
                },
                inputType = InputType.EMAIL,
                isSingleLine = true,
                labelValue = stringResource(R.string.email)
            )
            Spacer(modifier = Modifier.height(8.dp))
            SimpleTextField(
                textTyped = password,
                onValueChange = {
                    password = it
                },
                inputType = InputType.PASSWORD,
                isSingleLine = true,
                labelValue = stringResource(R.string.password)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(R.string.forgot_password)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onSignInClick,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = darkSteelBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.sign_in),
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "ó",
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Color.LightGray
                )
            }
            Button(
                onClick = onSignInClick,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = googleButtonColor,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Row {
                    Image(
                        painter = painterResource(R.drawable.google_color_icon),
                        contentDescription = stringResource(R.string.google_icon_description),
                        modifier = Modifier
                            .height(20.dp)
                            .width(20.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = stringResource(R.string.sign_in_with_google),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogInScreenPreview() {
    LogInScreen(state = SignInState(), onSignInClick = {})
}