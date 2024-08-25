package com.jecsdev.mywalletapp.ui.composables.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.jecsdev.mywalletapp.R
import com.jecsdev.mywalletapp.presentation.uihelpers.InputType
import com.jecsdev.mywalletapp.ui.theme.brownGrayColor
import com.jecsdev.mywalletapp.ui.theme.ghostColor
import com.jecsdev.mywalletapp.ui.theme.navyBlueColor

/**
 * Composable to use to create simple text fields.
 * @param textTyped is the text typed by the users.
 * @param labelValue is the hint to show in the text field.
 * @param isSingleLine determines if the text field is single lined or not.
 * @param inputType is the type of keyboard to show for typing.
 */
@Composable
fun SimpleTextField(
    textTyped: String?,
    labelValue: String?,
    onValueChange: (String) -> Unit,
    isSingleLine: Boolean,
    inputType: InputType,
    modifier: Modifier = Modifier
){
    var textValue by rememberSaveable {
        mutableStateOf(if (!textTyped.isNullOrEmpty()) textTyped else "")
    }
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        value = textValue,
        colors = TextFieldDefaults.colors(
            cursorColor = navyBlueColor,
            disabledLabelColor = navyBlueColor,
            focusedIndicatorColor = navyBlueColor,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = ghostColor,
            unfocusedContainerColor = ghostColor
        ),
        onValueChange = { value ->
            textValue = value
            onValueChange(value)
        },
        label = {
            Text(
                text = labelValue ?: stringResource(id = R.string.empty_string),
                color = brownGrayColor
            )
        },
        singleLine = isSingleLine,
        keyboardOptions = when (inputType) {
            InputType.TEXT -> KeyboardOptions(keyboardType = KeyboardType.Text)
            InputType.ASCII -> KeyboardOptions(keyboardType = KeyboardType.Ascii)
            InputType.NUMBER -> KeyboardOptions(keyboardType = KeyboardType.Number)
            InputType.PHONE -> KeyboardOptions(keyboardType = KeyboardType.Phone)
            InputType.URI -> KeyboardOptions(keyboardType = KeyboardType.Uri)
            InputType.EMAIL -> KeyboardOptions(keyboardType = KeyboardType.Email)
            InputType.PASSWORD -> KeyboardOptions(keyboardType = KeyboardType.Password)
            InputType.NUMBER_PASSWORD -> KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
            InputType.DECIMAL -> KeyboardOptions(keyboardType = KeyboardType.Decimal)
        }
    )
}

/**
 * Simple text field preview.
 */
@Composable
@Preview(showSystemUi = true)
fun SimpleTextFieldPreview() {
    SimpleTextField(
        stringResource(id = R.string.name),
        stringResource(id = R.string.last_name),
        {},
        true,
        InputType.TEXT,
        Modifier
    )
}