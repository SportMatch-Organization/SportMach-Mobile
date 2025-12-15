package com.example.sportmatch.ui.login.componentes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.sportmatch.ui.componentes.LoginTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.Alignment
import com.example.sportmatch.ui.viewModel.LoginViewModel
@Composable
fun LoginInputs(viewModel: LoginViewModel) {
    Text("Email", modifier = Modifier.padding(bottom = 4.dp))
    LoginTextField(
        value = viewModel.email,
        onValueChange = viewModel::onEmailChange,
        placeholder = "Digite seu email",
        isError = viewModel.emailError != null,
        errorMessage = viewModel.emailError,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        )
    )
    Spacer(Modifier.height(16.dp))
    Text("Senha", modifier = Modifier.padding(bottom = 4.dp))
    LoginTextField(
        value = viewModel.senha,
        onValueChange = viewModel::onSenhaChange,
        placeholder = "Digite sua senha",
        isError = viewModel.senhaError != null,
        errorMessage = viewModel.senhaError,
        visualTransformation = if (viewModel.passwordVisible)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        trailingIcon = {
            val icon = if (viewModel.passwordVisible) Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            IconButton(onClick = { viewModel.togglePasswordVisibility() }) {
                Icon(imageVector = icon, contentDescription = null)
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        )
    )
    Spacer(Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = viewModel.rememberMe,
                onCheckedChange = { viewModel.toggleRememberMe() }
            )
            Text("Lembrar minha senha")
        }
        Text(
            "Esqueci minha senha",
            modifier = Modifier.clickable { },
            color = Color.DarkGray
        )
    }
    Spacer(Modifier.height(32.dp))
}