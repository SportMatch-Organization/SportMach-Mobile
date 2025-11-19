package com.example.sportmatch.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.sportmatch.ui.theme.DisableColor

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(50.dp),
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = Color.White,
    enabled: Boolean = true,
    cornerRadius: Dp = 4.dp,
    textColor: Color = Color.White,
    icon: (@Composable (() -> Unit))? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = DisableColor,
            disabledContentColor = Color.Gray
        ),
        shape = RoundedCornerShape(cornerRadius)

    ) {
        Row(horizontalArrangement = Arrangement.Center){
            if (icon != null) {
                icon()
            Spacer(modifier = Modifier.width(16.dp))
            }
            CustomText(
                text = text,
                type = TextType.TITULO,
                color = textColor
            )
        }
    }
}
