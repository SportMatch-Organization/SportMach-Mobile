package com.example.sportmatch.ui.components
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String?,
    placeholder: String? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
    isPassword: Boolean = false,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    leadingIcon: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    space: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(autoCorrect = false),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val backgroundColor = Color(0xFFECECEC)
    val labelColor = Color(0xFF2E3E4B)

    if (space) Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { if (label != null) Text(label) },
        placeholder = { if (placeholder != null) Text(placeholder) },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        modifier = modifier,
        singleLine = singleLine,
        maxLines = maxLines,
        enabled = enabled,
        readOnly = readOnly,
        visualTransformation = if (isPassword && !passwordVisible)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,

        colors = TextFieldDefaults.colors(
            focusedContainerColor = backgroundColor,
            unfocusedContainerColor = backgroundColor,
            disabledContainerColor = Color.LightGray,
            errorContainerColor = backgroundColor,

            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,

            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            disabledTextColor = Color.Gray,
            errorTextColor = Color.Black,

            focusedLabelColor = labelColor,
            unfocusedLabelColor = labelColor,
            disabledLabelColor = labelColor.copy(alpha = 0.5f),
            errorLabelColor = MaterialTheme.colorScheme.error,

            focusedPlaceholderColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Gray,
        )
    )
}
