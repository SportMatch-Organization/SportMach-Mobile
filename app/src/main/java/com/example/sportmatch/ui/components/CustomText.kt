package com.example.sportmatch.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit

enum class TextType {
    LABEL,
    PARAGRAFO,
    SUBTITULO,
    TITULO,
    HEADLINE,
    DESTAQUE
}

@Composable
fun CustomText(
    text: String,
    type: TextType,
    color: Color =  MaterialTheme.colorScheme.tertiary,
    fontSize: TextUnit ? = null,
    fontWeight: FontWeight ? = null,
) {
    var modifier = Modifier;

    when (type) {
        TextType.LABEL -> {
            Text(
                text = text,
                fontSize = fontSize ?: 10.sp,
                fontWeight = FontWeight.Medium,
                color = color,
                modifier = modifier
            )
        }
        TextType.PARAGRAFO -> {
            Text(
                text = text,
                fontSize = fontSize ?: 12.sp,
                fontWeight = FontWeight.Medium,
                color = color,
                modifier = modifier
            )
        }
        TextType.SUBTITULO -> {
            Text(
                text = text,
                fontSize = fontSize ?: 16.sp,
                fontWeight = fontWeight ?: FontWeight.Medium,
                color = color,
                modifier = modifier
            )
        }
        TextType.TITULO -> {
            Text(
                text = text,
                fontSize = fontSize ?: 20.sp,
                fontWeight = FontWeight.Bold,
                color = color,
                modifier = modifier
            )
        }
        TextType.HEADLINE -> {
            Text(
                text = text,
                fontSize = fontSize ?: 30.sp,
                fontWeight = FontWeight.Bold,
                color = color,
                modifier = modifier
            )
        }
        TextType.DESTAQUE -> {
            Text(
                text = text,
                fontSize = fontSize ?: 35.sp,
                fontWeight = FontWeight.Bold,
                color = color,
                modifier = modifier
            )
        }
    }
}
