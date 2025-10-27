package com.example.cadastrologinsportmatch.ui

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Cadastro(
    onNavigateBack: () -> Unit,
    onCadastroSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var confirmEmail by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmSenha by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf<String?>(null) }
    var confirmEmailError by remember { mutableStateOf<String?>(null) }
    var senhaError by remember { mutableStateOf<String?>(null) }
    var confirmSenhaError by remember { mutableStateOf<String?>(null) }

    var senhaVisible by remember { mutableStateOf(false) }
    var confirmSenhaVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val laranjaColor = Color(0xFFF97316)
    val cinzaFundo = Color(0xFFF3F4F6)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = laranjaColor,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Cadastre-se",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Informe seu E-mail e crie uma senha",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text("E-mail", style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(bottom = 4.dp))
            TextField(
                value = email,
                onValueChange = { email = it; emailError = null },
                placeholder = { Text("Digite seu email", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = cinzaFundo, focusedContainerColor = cinzaFundo,
                    unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent, errorContainerColor = cinzaFundo
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = emailError != null
            )
            if (emailError != null) {
                Text(text = emailError!!, color = Color.Red, style = MaterialTheme.typography.bodySmall)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Repita o E-mail", style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(bottom = 4.dp))
            TextField(
                value = confirmEmail,
                onValueChange = { confirmEmail = it; confirmEmailError = null },
                placeholder = { Text("Repita seu e-mail", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = cinzaFundo, focusedContainerColor = cinzaFundo,
                    unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent, errorContainerColor = cinzaFundo
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = confirmEmailError != null
            )
            if (confirmEmailError != null) {
                Text(text = confirmEmailError!!, color = Color.Red, style = MaterialTheme.typography.bodySmall)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Crie uma senha", style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(bottom = 4.dp))
            TextField(
                value = senha,
                onValueChange = { senha = it; senhaError = null },
                placeholder = { Text("Crie uma senha", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = cinzaFundo, focusedContainerColor = cinzaFundo,
                    unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent, errorContainerColor = cinzaFundo
                ),
                singleLine = true,
                visualTransformation = if (senhaVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (senhaVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { senhaVisible = !senhaVisible }) { Icon(imageVector = image, null) }
                },
                isError = senhaError != null
            )
            if (senhaError != null) {
                Text(text = senhaError!!, color = Color.Red, style = MaterialTheme.typography.bodySmall)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Repita a senha", style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(bottom = 4.dp))
            TextField(
                value = confirmSenha,
                onValueChange = { confirmSenha = it; confirmSenhaError = null },
                placeholder = { Text("Repita sua senha", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = cinzaFundo, focusedContainerColor = cinzaFundo,
                    unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent, errorContainerColor = cinzaFundo
                ),
                singleLine = true,
                visualTransformation = if (confirmSenhaVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (confirmSenhaVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { confirmSenhaVisible = !confirmSenhaVisible }) { Icon(imageVector = image, null) }
                },
                isError = confirmSenhaError != null
            )
            if (confirmSenhaError != null) {
                Text(text = confirmSenhaError!!, color = Color.Red, style = MaterialTheme.typography.bodySmall)
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    var valido = true
                    emailError = null
                    confirmEmailError = null
                    senhaError = null
                    confirmSenhaError = null

                    if (email.isBlank()) {
                        emailError = "Digite o e-mail"
                        valido = false
                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        emailError = "E-mail inválido"
                        valido = false
                    }
                    if (email != confirmEmail) {
                        confirmEmailError = "Os e-mails não são iguais"
                        valido = false
                    }
                    if (senha.isBlank()) {
                        senhaError = "Digite a senha"
                        valido = false
                    } else if (senha.length < 8) {
                        senhaError = "Senha deve ter no mínimo 8 caracteres"
                        valido = false
                    } else if (senha.length > 12) {
                        senhaError = "Senha deve ter no máximo 12 caracteres"
                        valido = false
                    } else if (!senha.contains(Regex("[!@#\$%^&*(),.?\":{}|<>]"))) {
                        senhaError = "Senha deve conter ao menos 1 caractere especial"
                        valido = false
                    }
                    if (senha != confirmSenha) {
                        confirmSenhaError = "As senhas não são iguais"
                        valido = false
                    }
                    if (valido) {
                        isLoading = true
                        auth.createUserWithEmailAndPassword(email.trim(), senha.trim())
                            .addOnCompleteListener { task ->
                                isLoading = false
                                if (task.isSuccessful) {
                                    onCadastroSuccess()
                                } else {
                                    val errorMsg = task.exception?.message ?: "Erro desconhecido"
                                    if (errorMsg.contains("email address is already in use")) {
                                        emailError = "Este e-mail já está em uso"
                                    } else {
                                        Toast.makeText(context, "Falha no cadastro: $errorMsg", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(bottom = 24.dp), 
                colors = ButtonDefaults.buttonColors(containerColor = laranjaColor),
                shape = RoundedCornerShape(8.dp),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("Próximo", fontSize = 16.sp)
                }
            }
        }
    }
}