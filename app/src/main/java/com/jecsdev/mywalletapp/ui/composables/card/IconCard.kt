package com.jecsdev.mywalletapp.ui.composables.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jecsdev.mywalletapp.R
import com.jecsdev.mywalletapp.ui.theme.lightGrayColor


/**
 * Composable that show a card with an Icon only.
 */
@Composable
fun IconCard(painter: Painter, modifier: Modifier) {
    Card(modifier = modifier.size(80.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = lightGrayColor),
        ) {

        Image(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            painter = painter,
            contentDescription = stringResource(R.string.loan_icon)
        )
    }
}