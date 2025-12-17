package com.example.sportmatch.ui.login.componentes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
@Composable
fun LoginHeader() {
    Spacer(Modifier.height(16.dp))
    Text(
        "Acesse",
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold
    )
    Text(
        "Com E-mail e senha para entrar",
        color = Color.Gray
    )
    Spacer(Modifier.height(32.dp))
}