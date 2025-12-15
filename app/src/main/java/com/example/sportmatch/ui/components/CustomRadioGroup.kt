package com.example.sportmatch.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomRadioGroup(
    label: String,
    options: List<String>,
    selectedValue: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    space: Boolean = true
) {
    if (space) {
        Spacer(modifier = Modifier.height(16.dp))
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        CustomText(
            text = label,
            type = TextType.SUBTITULO,
            color = MaterialTheme.colorScheme.secondary
        )

        options.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = selectedValue == option,
                    onClick = { if (enabled) onValueChange(option) },
                    enabled = enabled,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary,
                        unselectedColor = MaterialTheme.colorScheme.secondary,
                        disabledSelectedColor = MaterialTheme.colorScheme.surfaceVariant,
                        disabledUnselectedColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )

                CustomText(
                    text = option,
                    type = TextType.SUBTITULO
                )
            }
        }
    }
}
