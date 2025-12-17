package com.example.sportmatch.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomCheckBoxGroup(
    options: List<String>,
    selectedOptions: List<String>,
    onSelectionChange: (List<String>) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    title: String? = null
) {
    Column(modifier = modifier) {

        if (title != null) {
            CustomText(
                title, TextType.SUBTITULO
            )
        }

        options.forEach { option ->
            val isChecked = selectedOptions.contains(option)

            androidx.compose.foundation.layout.Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { checked ->
                        val newSelection = if (checked) {
                            selectedOptions + option
                        } else {
                            selectedOptions - option
                        }
                        onSelectionChange(newSelection)
                    },
                    enabled = enabled,
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary,
                        uncheckedColor =  MaterialTheme.colorScheme.secondary,
                        checkmarkColor = Color.White
                    )
                )

                CustomText(
                    option, TextType.SUBTITULO
                )
            }
        }
    }
}
