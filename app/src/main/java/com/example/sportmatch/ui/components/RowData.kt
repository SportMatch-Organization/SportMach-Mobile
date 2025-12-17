package com.example.sportmatch.ui.components

import android.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RowData (label: String, value: String){
    Row{
        CustomText(label+":", TextType.SUBTITULO, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.width(8.dp))
        CustomText(value, TextType.SUBTITULO, color = Color(0xFF8C8C8C), fontWeight = FontWeight.Normal)
    }
}