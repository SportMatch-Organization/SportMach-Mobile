package com.example.sportmatch.ui.cadastro

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import com.example.sportmatch.database.service.AuthService // Importa o serviço do Firebase

@Composable
fun Cadastro6(
    authService: AuthService = remember { AuthService() },

    emailFinal: String = "novo.usuario@teste.com",
    senhaFinal: String = "senha123456",

    onNavigateToLogin: () -> Unit
) {

    val context = LocalContext.current
    var isCadastroLoading by remember { mutableStateOf(false) }
    var futebol by remember { mutableStateOf("Futebol") }

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(24.dp),
        verticalArrangement = Arrangement.Top){

        Text(
            text = "Quais os seus esportes de interesse?",
            color = Color.Black,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(64.dp))

        Button(onClick = { /* Lógica de esporte aqui */ }) {
            Text(futebol)
        }

        Spacer(modifier = Modifier.height(512.dp))

        Button(
            onClick = {
                isCadastroLoading = true

                if (emailFinal.isBlank() || senhaFinal.isBlank()) {
                    Toast.makeText(context, "Erro: E-mail ou senha não encontrados.", Toast.LENGTH_LONG).show()
                    isCadastroLoading = false
                    return@Button
                }

                authService.cadastrarUsuario(emailFinal, senhaFinal) { sucesso, uid, erroMsg ->
                    isCadastroLoading = false
                    if (sucesso) {
                        Toast.makeText(context, "Cadastro OK! UID: $uid", Toast.LENGTH_LONG).show()
                        onNavigateToLogin() // Navega para o Login
                    } else {
                        Toast.makeText(context, "Falha no Cadastro: $erroMsg", Toast.LENGTH_LONG).show()
                    }
                }
            },
            enabled = !isCadastroLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isCadastroLoading) "Cadastrando..." else "Cadastrar")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Cadastro6Preview(){
    Cadastro6(emailFinal = "", senhaFinal = "", onNavigateToLogin = {})
}