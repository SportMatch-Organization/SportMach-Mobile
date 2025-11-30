package com.example.sportmatch.ui.login.componentes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
@Composable
fun LoginButtons(
    isLoading: Boolean,
    onLoginClick: () -> Unit,
    onNavigateToCadastro: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            enabled = !isLoading,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF97316))
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text("Acessar")
            }
        }
        OutlinedButton(
            onClick = onNavigateToCadastro,
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Text(
                "Cadastrar",
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold
            )
        }
    }
}