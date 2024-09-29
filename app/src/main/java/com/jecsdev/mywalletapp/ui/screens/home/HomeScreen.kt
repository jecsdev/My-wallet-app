package com.jecsdev.mywalletapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.jecsdev.auth.domain.entities.User
import com.jecsdev.mywalletapp.R
import com.jecsdev.mywalletapp.ui.composables.card.BalanceCard
import com.jecsdev.mywalletapp.ui.composables.card.BorrowerTransactionsResumeCard
import com.jecsdev.mywalletapp.ui.composables.card.IconCard
import com.jecsdev.mywalletapp.ui.composables.card.QuantityCard
import com.jecsdev.mywalletapp.ui.theme.lightLavender

/**
 * DashboardScreen view.
 */
@Composable
fun HomeScreen(
    userData: User?,
    onSignOut: () -> Unit,
    navController: NavController?
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightLavender)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (userData?.profilePictureUri != null && userData.userName?.isNotEmpty() == true) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(3f)
                ) {
                    AsyncImage(
                        model = userData.profilePictureUri,
                        contentDescription = stringResource(R.string.profile_picture),
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.greeting_with_comma) +
                                " " + userData.userName,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
            IconButton(
                onClick = onSignOut,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(48.dp)
            ) {

                Icon(
                    modifier = Modifier
                        .padding(start = 4.dp, top = 4.dp),
                    painter = painterResource(id = R.drawable.power_icon_24),
                    contentDescription = stringResource(id = R.string.power_icon)
                )
            }

        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.account_status),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            QuantityCard(
                headerText = stringResource(R.string.active_plannings),
                quantity = 200, modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            BalanceCard(
                headerText = stringResource(R.string.credited_balance),
                balance = 150000.00, modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.features),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.width(80.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconCard(
                    painter = painterResource(id = R.drawable.loan_icon_action),
                    modifier = Modifier.clickable { navController?.let { } }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.create_loan),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = Modifier.width(80.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconCard(
                    painter = painterResource(id = R.drawable.deposit_icon),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.deposit),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = Modifier.width(80.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconCard(painter = painterResource(id = R.drawable.user_icon),
                    modifier = Modifier.clickable {

                    }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.manage_borrowers),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.recent_transactions),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(5) {
                BorrowerTransactionsResumeCard()
            }
        }
    }
}

/*
 * Dashboard Screen preview with dummy data.
 */
@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    HomeScreen(
        userData = User(
            userName = stringResource(R.string.john_doe),
            userId = stringResource(R.string.sample_number),
            profilePictureUri = stringResource(id = R.string.empty_string)
        ),
        onSignOut = {},
        navController = null
    )
}


