package com.example.sportmatch.ui.components

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomMultiSelectField(
    label: String,
    options: List<String>,
    selectedValues: List<String>,
    onValueChange: (List<String>) -> Unit,
    enabled: Boolean = true,
    space: Boolean = true,
) {
    var expanded by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    if (space) Spacer(modifier = Modifier.height(16.dp))

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { if (enabled) {
            expanded = !expanded
            focusManager.clearFocus()
        }}
    ) {
        CustomTextField(
            value = if (selectedValues.isEmpty()) "" else selectedValues.joinToString(", "),
            onValueChange = {},
            readOnly = true,
            label = label,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .clickable {
                    if (enabled) {
                        expanded = !expanded
                        focusManager.clearFocus()
                    }
                },
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color(0xFFEEF8FF))
        ) {
            options.forEach { option ->
                val isSelected = selectedValues.contains(option)

                DropdownMenuItem(
                    text = {
                        CustomCheckBox(
                            checked = isSelected,
                            onCheckedChange = { checked ->
                                val newSelection = if (checked) {
                                    selectedValues + option
                                } else {
                                    selectedValues - option
                                }
                                onValueChange(newSelection)
                            },
                            label = option
                        )
                    },
                    onClick = {}
                )
            }
        }
    }
}
