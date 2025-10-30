package com.example.cadastrologinsportmatch.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.sportmatch.ui.components.CustomText
import com.example.sportmatch.ui.components.TextType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSelectField(
    label: String,
    options: List<String>,
    selectedValue: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    space: Boolean = true,
) {
    var expanded by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    if (space) Spacer(modifier = Modifier.height(16.dp))

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { if(enabled) {
            expanded = !expanded
            focusManager.clearFocus()
        } }
    ) {
        CustomTextField(
            value = selectedValue,
            onValueChange = {},
            readOnly = true,
            label = (label),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .clickable { // ✅ controla manualmente a abertura
                    expanded = !expanded
                    focusManager.clearFocus() // evita sugestões
                },
            keyboardOptions = KeyboardOptions.Default, // ✅ não abre teclado
            keyboardActions = KeyboardActions.Default,
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
                    modifier = Modifier.background(Color(0xFFEEF8FF))
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { CustomText(option, TextType.SUBTITULO, color = Color.Black) },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                        focusManager.clearFocus()
                    }
                )
            }
        }
    }
}
